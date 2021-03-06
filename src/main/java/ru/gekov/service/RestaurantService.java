package ru.gekov.service;

import ru.gekov.model.Restaurant;
import ru.gekov.to.RestaurantVoteCountTo;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    List<Restaurant> getAll();
    List<Restaurant> getAllWithMenuDishes();
    List<Restaurant> getAllWithVotes();
    List<Restaurant> getAllWithMenuDishesAndVotes();

    Restaurant get(Integer id);
    List<Restaurant> getByMenuDate(LocalDate date);

    List<RestaurantVoteCountTo> getWithVotesCountByDate(LocalDate date);

    Restaurant getWithMenuDishesById(int id);
    Restaurant getWithVotesById(int id);

    Restaurant getWithMenuDishesAndVotesById(int id);

    Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date);

    Restaurant getWithVotesByIdAndDate(int id, LocalDate date);

    RestaurantVoteCountTo getWithVotesCountByIdAndDate(int id, LocalDate date);

    Restaurant getWithMenuDishesAndVotesByIdAndDate(int id, LocalDate date);

    List<Restaurant> getWithMenuDishesByDate(LocalDate date);

    List<Restaurant> getWithVotesByDate(LocalDate date);

    void update(Restaurant restaurant);
    Restaurant create(Restaurant restaurant);
    void delete(int id);
}
