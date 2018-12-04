package ru.gekov.service;

import ru.gekov.model.MenuDish;
import ru.gekov.to.DateMenuTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuDishService {

    List<MenuDish> getAll();
    List<MenuDish> getAllByDate(LocalDate date);
    List<MenuDish> getAllByRestaurantId(int restaurant_id);
    List<MenuDish> getByDateAndRestaurantId(LocalDate date, int restaurant_id);
    MenuDish getById(int id);

    MenuDish update(MenuDish menuDish, int dishId, int restaurantId);

    MenuDish create(MenuDish menuDish, int dishId, int restaurantId);

    boolean delete(int id);

}
