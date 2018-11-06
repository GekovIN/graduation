package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.Meal;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.MealRepository;
import ru.gekov.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository, RestaurantRepository restaurantRepository) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Meal> getAll(LocalDate date) {
        return mealRepository.findAllByDate(date);
    }

    @Override
    public List<Meal> getAll(int restaurant_id) {
        return mealRepository.findAllByRestaurantId(restaurant_id);
    }

    @Override
    public List<Meal> getAll(LocalDate date, int restaurant_id) {
        return mealRepository.findAllByDateAndRestaurantId(date, restaurant_id);
    }

    @Override
    public Meal get(int id) {
        return mealRepository.findById(id).orElse(null);
    }

    @Override
    public Meal update(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal create(Meal meal, int restaurant_id) {
        Restaurant restaurant = restaurantRepository.getOne(restaurant_id);
        meal.setRestaurant(restaurant);
        return mealRepository.save(meal);
    }

    @Override
    public boolean delete(int id) {
        return mealRepository.delete(id) != 0;
    }
}
