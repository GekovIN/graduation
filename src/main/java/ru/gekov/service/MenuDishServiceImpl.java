package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Dish;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.DishRepository;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.to.MenuDishTo;
import ru.gekov.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.gekov.util.ValidationUtil.checkNew;
import static ru.gekov.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuDishServiceImpl implements MenuDishService {

    private final MenuDishRepository menuDishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Autowired
    public MenuDishServiceImpl(MenuDishRepository menuDishRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.menuDishRepository = menuDishRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<MenuDish> getAll() {
        return menuDishRepository.findAll();
    }

    @Override
    public List<MenuDish> getAllByDate(LocalDate date) {
        return menuDishRepository.findAllByDate(date);
    }

    @Override
    public MenuDish getById(int id) {
        return checkNotFoundWithId(menuDishRepository.findById(id).orElse(null), id);
    }

    @Override
    public void update(MenuDishTo menuDishTo) {
        ValidationUtil.validateMenuDishTo(menuDishTo);

        MenuDish menuDish = getById(menuDishTo.getId());
        Dish dish = dishRepository.getOne(menuDishTo.getDishId());
        Restaurant restaurant = restaurantRepository.getOne(menuDishTo.getRestaurantId());
        menuDish.setDish(dish);
        menuDish.setRestaurant(restaurant);
        menuDish.setDate(menuDishTo.getDate());
        menuDishRepository.save(menuDish);
    }

    @Override
    @Transactional
    public MenuDish create(MenuDishTo menuDishTo) {
        ValidationUtil.validateMenuDishTo(menuDishTo);

        checkNew(menuDishTo);
        Dish dish = dishRepository.getOne(menuDishTo.getDishId());
        Restaurant restaurant = restaurantRepository.getOne(menuDishTo.getRestaurantId());
        return initLazyObj(menuDishRepository.save(new MenuDish(menuDishTo.getDate(), dish, restaurant)));
    }

    //init lazy restaurant and dish after saving
    private MenuDish initLazyObj(MenuDish menuDish) {
        menuDish.getDish().toString();
        menuDish.getRestaurant().toString();
        return menuDish;
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(menuDishRepository.delete(id) != 0, id);
    }
}
