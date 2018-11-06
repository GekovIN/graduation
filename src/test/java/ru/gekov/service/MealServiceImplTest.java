package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.Meal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static ru.gekov.MealTestData.*;
import static ru.gekov.RestaurantTestData.EURO_REST_ID;
import static ru.gekov.RestaurantTestData.THAI_REST_ID;

public class MealServiceImplTest extends ServiceTest {

    @Autowired
    MealService service;

    @Test
    public void getAllByDate() {
        List<Meal> meals = service.getAll(LocalDate.of(2018, 10, 29));
        assertMatch(meals, EURO_MEALS_2018_10_29);
    }

    @Test
    public void getAllByRestaurantId() {
        List<Meal> meals = service.getAll(EURO_REST_ID);
        assertMatch(meals, EURO_MEALS_2018_10_29);
    }

    @Test
    public void getAllByDateAndRestaurantId() {
        List<Meal> meals = service.getAll(LocalDate.of(2018, 10, 30), THAI_REST_ID);
        assertMatch(meals, THAI_MEALS_2018_10_30);
    }

    @Test
    public void get() {
        Meal meal = service.get(EURO_MEAL_1_ID);
        assertMatch(meal, EURO_MEAL_1);
    }

    @Test
    public void update() {
        Meal meal = service.get(EURO_MEAL_1_ID);
        meal.setDescription("Changed description");
        service.update(meal);
        meal = service.get(EURO_MEAL_1_ID);
        assertMatch(meal, CHANGED_MEAL);
    }

    @Test
    public void create() {
        Meal meal = new Meal(LocalDate.of(2018, 11, 1), "New Meal", new BigDecimal(10000));
        service.create(meal, EURO_REST_ID);
        meal = service.get(CREATED_MEAL.getId());
        assertMatch(meal, CREATED_MEAL);
    }

    @Test
    public void delete() {
        service.delete(EURO_MEAL_3_ID);
        List<Meal> meals = service.getAll(EURO_REST_ID);
        assertMatch(meals, List.of(EURO_MEAL_1, EURO_MEAL_2));
    }
}