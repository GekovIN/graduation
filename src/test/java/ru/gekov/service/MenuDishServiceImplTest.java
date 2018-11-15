package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.MenuDish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static ru.gekov.MealTestData.*;
import static ru.gekov.RestaurantTestData.EURO_REST_ID;
import static ru.gekov.RestaurantTestData.THAI_REST_ID;

public class MenuDishServiceImplTest extends ServiceTest {

    @Autowired
    MenuDishService service;

    @Test
    public void getAllByDate() {
        List<MenuDish> menuDishes = service.getAll(LocalDate.of(2018, 10, 29));
        assertMatch(menuDishes, EURO_MEALS_20181029);
    }

    @Test
    public void getAllByRestaurantId() {
        List<MenuDish> menuDishes = service.getAll(EURO_REST_ID);
        assertMatch(menuDishes, EURO_MEALS_20181029);
    }

    @Test
    public void getAllByDateAndRestaurantId() {
        List<MenuDish> menuDishes = service.getAll(LocalDate.of(2018, 10, 30), THAI_REST_ID);
        assertMatch(menuDishes, THAI_MEALS_20181030);
    }

    @Test
    public void get() {
        MenuDish menuDish = service.getById(EURO_MEAL_1_ID);
        assertMatch(menuDish, EURO_MENU_DISHES_1);
    }

    @Test
    public void update() {
        MenuDish menuDish = service.getById(EURO_MEAL_1_ID);
        menuDish.setName("Changed description");
        service.update(menuDish);
        menuDish = service.getById(EURO_MEAL_1_ID);
        assertMatch(menuDish, CHANGED_MENU_DISHES);
    }

    @Test
    public void create() {
        MenuDish menuDish = new MenuDish(LocalDate.of(2018, 11, 1), "New MenuDish", new BigDecimal(10000));
        service.create(menuDish, EURO_REST_ID);
        menuDish = service.getById(CREATED_MENU_DISHES.getId());
        assertMatch(menuDish, CREATED_MENU_DISHES);
    }

    @Test
    public void delete() {
        service.delete(EURO_MEAL_3_ID);
        List<MenuDish> menuDishes = service.getAll(EURO_REST_ID);
        assertMatch(menuDishes, List.of(EURO_MENU_DISHES_1, EURO_MENU_DISHES_2));
    }
}