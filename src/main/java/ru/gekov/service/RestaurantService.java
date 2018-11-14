package ru.gekov.service;

import ru.gekov.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    Restaurant get(Integer id);
    Restaurant getWithMeals(Integer id);
    Restaurant update(Restaurant restaurant);
    Restaurant create(Restaurant restaurant);
    Restaurant simpleGet(Integer id);
    boolean delete(int id);

}
