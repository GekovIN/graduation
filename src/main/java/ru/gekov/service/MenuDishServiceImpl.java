package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.Dish;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.DishRepository;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

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
    public List<MenuDish> getAll(LocalDate date) {
        return menuDishRepository.findAllByDate(date);
    }

    @Override
    public List<MenuDish> getAll(int restaurantId) {
        return menuDishRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public List<MenuDish> getAll(LocalDate date, int restaurantId) {
        return menuDishRepository.findAllByDateAndRestaurantId(date, restaurantId);
    }

    @Override
    public MenuDish getById(int id) {
        return menuDishRepository.findById(id).orElse(null);
    }

    @Override
    public MenuDish update(MenuDish menuDish, int dishId, int restaurantId) {
        Dish dish = dishRepository.getOne(dishId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        menuDish.setDish(dish);
        menuDish.setRestaurant(restaurant);
        return menuDishRepository.save(menuDish);
    }

    @Override
    public MenuDish create(LocalDate date, int dishId, int restaurantId) {
        Dish dish = dishRepository.getOne(dishId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        MenuDish menuDish = new MenuDish(date, dish, restaurant);
        return menuDishRepository.save(menuDish);
    }

    @Override
    public boolean delete(int id) {
        return menuDishRepository.delete(id) != 0;
    }
}
