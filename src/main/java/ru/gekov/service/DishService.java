package ru.gekov.service;

import ru.gekov.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAll();
    Dish getById(int id);
    Dish save(Dish dish);
    boolean delete(int id);
}
