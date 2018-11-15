package ru.gekov.service;

import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    Restaurant get(Integer id);
    Restaurant getWithMenuDishes(int id);
    Restaurant getWithMenuDishes(int id, LocalDate date);
    Restaurant save(Restaurant restaurant);
    boolean delete(int id);

}
