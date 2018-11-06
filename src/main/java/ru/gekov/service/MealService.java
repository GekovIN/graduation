package ru.gekov.service;

import ru.gekov.model.Meal;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface MealService {

    List<Meal> getAll(Date date);
    List<Meal> getAll(int restaurant_id);
    List<Meal> getAll(Date date, int restaurant_id);
    Meal get(int id);

    Meal update(Meal meal);
    Meal create(Meal meal);
    boolean delete(int id);

}
