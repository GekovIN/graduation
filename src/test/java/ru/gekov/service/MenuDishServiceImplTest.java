package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.MenuDish;

import java.time.LocalDate;
import java.util.List;

import static ru.gekov.DishTestData.*;
import static ru.gekov.MenuDishTestData.*;
import static ru.gekov.RestaurantTestData.*;

public class MenuDishServiceImplTest extends ServiceTest {

    @Autowired
    private MenuDishService service;

    @Test
    public void getAll() {
        List<MenuDish> all = service.getAll();
        assertMatch(all, ALL_MENU);
    }

//    @Test
//    public void getAllByDate() {
//        List<MenuDish> all = service.getAllByDate(LocalDate.of(2018, 10, 31));
//        MenuDishUtil.getDateMenus(LocalDate.of(2018, 10, 31), all);
//        assertMatch(all, EURO_MENU_2018_10_29);
//    }

    @Test
    public void getAllByRestaurantId() {
        List<MenuDish> all = service.getAllByRestaurantId(EURO_REST_ID);
        assertMatch(all, EURO_MENU_2018_10_29);
    }

//    @Test
//    public void getAllByDateAndId() {
//        List<MenuDish> all = service.getByDateAndRestaurantId(LocalDate.of(2018, 10, 30), THAI_REST_ID);
//        assertMatch(all, THAI_MENU_2018_10_30);
//    }

    @Test
    public void getById() {
        MenuDish menuDish = service.getById(EURO_MENU_DISH_1_ID);
        assertMatch(menuDish, EURO_MENU_DISH_1);
    }

//    @Test
//    public void create() {
//        MenuDish newMenuDish = new MenuDish(RUSS_MENU_DISH_3_ID+1, LocalDate.of(2018, 1, 1), EURO_DISH_1, EURO_REST);
//        MenuDish created = service.create(LocalDate.of(2018, 1, 1), EURO_DISH_1_ID, EURO_REST_ID);
//        MenuDish dish = service.getById(created.getId());
//        assertMatch(dish, newMenuDish);
//    }

//    @Test
//    public void update() {
//        MenuDish oldMenuDish = service.getById(EURO_MENU_DISH_1_ID);
//        MenuDish updated = new MenuDish(oldMenuDish.getId(), oldMenuDish.getDate(), THAI_DISH_1, THAI_REST);
//        service.update(oldMenuDish, THAI_DISH_1_ID, THAI_REST_ID);
//        MenuDish newMenuDish = service.getById(EURO_MENU_DISH_1_ID);
//        assertMatch(newMenuDish, updated);
//    }

    @Test
    public void delete() {
        service.delete(EURO_MENU_DISH_1_ID);
        List<MenuDish> all = service.getAll();
        assertMatch(all, ALL_MENU_EXCEPT_FIRST);
    }
}