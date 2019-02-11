package ru.gekov.service;

import ru.gekov.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Vote get(int id);

    List<Vote> getAll();

    Vote getByUserAndDate(int userId, LocalDate date);

    Long countAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Vote save(LocalDateTime dateTime, int userId, int restaurantId);

    void delete(int id);
}
