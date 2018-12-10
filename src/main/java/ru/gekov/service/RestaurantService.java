package ru.gekov.service;

import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    Restaurant get(Integer id);
    Restaurant getWithMenuDishesById(int id);
    Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date);

    List<Restaurant> getWithMenuDishesByDate(LocalDate date);

    Restaurant update(Restaurant restaurant);
    Restaurant create(Restaurant restaurant);
    void delete(int id);

}
