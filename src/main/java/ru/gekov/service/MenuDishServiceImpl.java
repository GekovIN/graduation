package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.Dish;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.DishRepository;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.to.DateMenuTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<MenuDish> getByDateAndRestaurantId(LocalDate date, int restaurantId) {
        List<MenuDish> menuDishes = menuDishRepository.findAllByDateAndRestaurantId(date, restaurantId);

        return menuDishes;
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
    public MenuDish create(MenuDish menuDish, int dishId, int restaurantId) {
        Dish dish = dishRepository.getOne(dishId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        menuDish.setDish(dish);
        menuDish.setRestaurant(restaurant);
        return menuDishRepository.save(menuDish);
    }

    @Override
    public boolean delete(int id) {
        return menuDishRepository.delete(id) != 0;
    }
}
