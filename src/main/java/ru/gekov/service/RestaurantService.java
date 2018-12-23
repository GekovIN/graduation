package ru.gekov.service;

import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    List<Restaurant> getAllWithMenuDishes();
    List<Restaurant> getAllWithVotes();
    List<Restaurant> getAllWithMenuDishesAndVotes();

    Restaurant get(Integer id);
    List<Restaurant> getByDate(LocalDate date);
    Restaurant getWithMenuDishesById(int id);
    Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date);
    List<Restaurant> getWithMenuDishesByDate(LocalDate date);

    Restaurant update(Restaurant restaurant);
    Restaurant create(Restaurant restaurant);
    void delete(int id);
}
