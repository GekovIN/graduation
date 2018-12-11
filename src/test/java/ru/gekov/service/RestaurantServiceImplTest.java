package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.MenuDishTestData;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static ru.gekov.MenuDishTestData.EURO_MENU_2018_10_29;
import static ru.gekov.MenuDishTestData.THAI_MENU_2018_10_30;
import static ru.gekov.RestaurantTestData.*;

public class RestaurantServiceImplTest extends ServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, ALL_RESTAURANTS);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(EURO_REST_ID);
        assertMatch(restaurant, EURO_REST);
    }

    @Test
    public void getWithMenuDishesById() {
        Restaurant restaurant = service.getWithMenuDishesById(EURO_REST_ID);
        List<MenuDish> menuDishes = restaurant.getMenuDishes();
        assertMatch(restaurant, EURO_REST);
        MenuDishTestData.assertMatch(menuDishes, EURO_MENU_2018_10_29);
    }

//    @Test
//    public void getWithMenuDishesByIdAndDate() {
//        Restaurant restaurant = service.getWithMenuDishesByIdAndDate(THAI_REST_ID, LocalDate.of(2018, 10, 30));
//        List<MenuDish> menuDishes = restaurant.getMenuDishes();
//        assertMatch(restaurant, THAI_REST);
//        MenuDishTestData.assertMatch(menuDishes, THAI_MENU_2018_10_30);
//    }

    @Test
    public void create() {
        Restaurant savedRest = service.create(new Restaurant("New restaurant", "New address"));
        Restaurant restaurant = service.get(savedRest.getId());
        assertMatch(restaurant, savedRest);
    }

    @Test
    public void update() {
        Restaurant restaurant = service.get(EURO_REST_ID);
        restaurant.setName("Updated rest");
        service.create(restaurant);
        Restaurant updatedRest = service.get(EURO_REST_ID);
        assertMatch(updatedRest, restaurant);
    }

    @Test
    public void delete() {
        service.delete(EURO_REST_ID);
        List<Restaurant> all = service.getAll();
        assertMatch(all, ALL_RESTAURANTS_EXCEPT_FIRST);
    }
}