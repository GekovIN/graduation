package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.Dish;

import java.math.BigDecimal;
import java.util.List;

import static ru.gekov.DishTestData.*;

public class DishServiceImplTest extends ServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void getAll() {
        List<Dish> dishes = service.getAll();
        assertMatch(dishes, ALL_DISHES);
    }

    @Test
    public void getById() {
        Dish dish = service.getById(EURO_DISH_1_ID);
        assertMatch(dish, EURO_DISH_1);
    }

    @Test
    public void create() {
        Dish saved_dish = service.save(new Dish("New Dish", new BigDecimal(1000)));
        Dish dish = service.getById(saved_dish.getId());
        assertMatch(dish, saved_dish);
    }

    @Test
    public void update() {
        Dish dish = service.getById(EURO_DISH_1_ID);
        dish.setName("Updated dish");
        service.save(dish);
        Dish updatedDish = service.getById(EURO_DISH_1_ID);
        assertMatch(dish, updatedDish);
    }

    @Test
    public void delete() {
        service.delete(EURO_DISH_1_ID);
        List<Dish> all = service.getAll();
        assertMatch(all, ALL_DISHES_EXCEPT_FIRST);
    }
}