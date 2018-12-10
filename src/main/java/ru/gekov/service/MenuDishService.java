package ru.gekov.service;

import ru.gekov.model.MenuDish;
import ru.gekov.to.MenuDishTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuDishService {

    List<MenuDish> getAll();
    List<MenuDish> getAllByDate(LocalDate date);
    List<MenuDish> getAllByRestaurantId(int restaurant_id);
    MenuDish getById(int id);
    MenuDish update(MenuDishTo menuDishTo);
    MenuDish create(MenuDishTo menuDishTo);
    void delete(int id);

}
