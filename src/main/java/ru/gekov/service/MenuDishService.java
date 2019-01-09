package ru.gekov.service;

import ru.gekov.model.MenuDish;
import ru.gekov.to.MenuDishTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuDishService {

    List<MenuDish> getAll();
    List<MenuDish> getAllByDate(LocalDate date);
    MenuDish getById(int id);
    void update(MenuDishTo menuDishTo);
    MenuDish create(MenuDishTo menuDishTo);
    void delete(int id);

}
