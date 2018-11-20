package ru.gekov.service;

import ru.gekov.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Vote get(int id);
    List<Vote> getAll();
    List<Vote> getByDate(LocalDate date);
    List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date);
    Vote getByUserAndDate(int userId, LocalDate date);
    Vote save(LocalDateTime dateTime, int userId, int restaurantId);
    boolean delete(int id);
    Long countVotes(LocalDate date, int restaurantId);
}
