package ru.gekov.service;

import ru.gekov.model.Meal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MealService {

    List<Meal> getAll(LocalDate date);
    List<Meal> getAll(int restaurant_id);
    List<Meal> getAll(LocalDate date, int restaurant_id);
    Meal get(int id);

    Meal update(Meal meal);
    Meal create(Meal meal, int restaurant_id);
    boolean delete(int id);

}
