package ru.gekov.service;

import ru.gekov.model.MenuDish;

import java.time.LocalDate;
import java.util.List;

public interface MenuDishService {

    List<MenuDish> getAll(LocalDate date);
    List<MenuDish> getAll(int restaurant_id);
    List<MenuDish> getAll(LocalDate date, int restaurant_id);
    MenuDish getById(int id);

    MenuDish update(MenuDish menuDish, int dishId, int restaurantId);

    MenuDish create(LocalDate date, int dishId, int restaurantId);

    boolean delete(int id);

}
