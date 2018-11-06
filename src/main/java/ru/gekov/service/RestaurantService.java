package ru.gekov.service;

import ru.gekov.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    Restaurant get(int id);
    Restaurant update(Restaurant restaurant);
    Restaurant create(Restaurant restaurant);
    boolean delete(int id);

}
