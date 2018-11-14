package ru.gekov.service;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.Meal;
import ru.gekov.model.Restaurant;

import static org.junit.Assert.*;
import static ru.gekov.MealTestData.EURO_MEAL_1;
import static ru.gekov.MealTestData.EURO_MEAL_1_ID;
import static ru.gekov.MealTestData.assertMatch;
import static ru.gekov.RestaurantTestData.EURO_REST_ID;

public class RestaurantServiceImplTest extends ServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void getWithMeals() {
        Restaurant restaurant = service.getWithMeals(EURO_REST_ID);
        System.out.println(restaurant.getMeals());
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(EURO_REST_ID);
        System.out.println(restaurant);
    }
    @Test
    public void simpleGet() {
        Restaurant restaurant = service.simpleGet(EURO_REST_ID);
        System.out.println(restaurant.getMeals());
    }

}