package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.Dish;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.DishRepository;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.to.MenuDishTo;
import ru.gekov.util.NotFoundException;
import ru.gekov.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.gekov.util.ValidationUtil.*;
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
    public List<MenuDish> getAllByRestaurantId(int restaurantId) {
        return menuDishRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public MenuDish getById(int id) {
        return checkNotFoundWithId(menuDishRepository.findById(id).orElse(null), id);
    }

    @Override
    public MenuDish update(MenuDishTo menuDishTo) {
        Assert.notNull(menuDishTo, "menuDishTo must not be null");
        Assert.notNull(menuDishTo.getId(), "menuDishTo.id must not be null");
        Assert.notNull(menuDishTo.getDishId(), "menuDishTo.dishId must not be null");
        Assert.notNull(menuDishTo.getRestaurantId(), "menuDishTo.restaurantId must not be null");

        MenuDish menuDish = getById(menuDishTo.getId());
        Dish dish = dishRepository.getOne(menuDishTo.getDishId());
        Restaurant restaurant = restaurantRepository.getOne(menuDishTo.getRestaurantId());
        menuDish.setDish(dish);
        menuDish.setRestaurant(restaurant);
        menuDish.setDate(menuDishTo.getDate());
        return menuDishRepository.save(menuDish);
    }

    @Override
    public MenuDish create(MenuDishTo menuDishTo) {
        Assert.notNull(menuDishTo, "Menu Dish must not be null");
        Assert.notNull(menuDishTo.getId(), "menuDishTo.id must not be null");
        Assert.notNull(menuDishTo.getDishId(), "menuDishTo.dishId must not be null");
        Assert.notNull(menuDishTo.getRestaurantId(), "menuDishTo.restaurantId must not be null");

        checkNew(menuDishTo);
        Dish dish = dishRepository.getOne(menuDishTo.getDishId());
        Restaurant restaurant = restaurantRepository.getOne(menuDishTo.getRestaurantId());
        return menuDishRepository.save(new MenuDish(menuDishTo.getDate(), dish, restaurant));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(menuDishRepository.delete(id) != 0, id);
    }
}
