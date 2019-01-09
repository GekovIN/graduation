package ru.gekov;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.gekov.model.MenuDish;
import ru.gekov.to.MenuDishTo;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.DishTestData.*;
import static ru.gekov.RestaurantTestData.EURO_REST;
import static ru.gekov.RestaurantTestData.RUSS_REST;
import static ru.gekov.RestaurantTestData.THAI_REST;

public class MenuDishTestData {

    public static final int EURO_MENU_DISH_1_ID = 100000;
    public static final int EURO_MENU_DISH_2_ID = EURO_MENU_DISH_1_ID +1;
    public static final int EURO_MENU_DISH_3_ID = EURO_MENU_DISH_1_ID +2;

    public static final int THAI_MENU_DISH_1_ID = EURO_MENU_DISH_1_ID +3;
    public static final int THAI_MENU_DISH_2_ID = EURO_MENU_DISH_1_ID +4;
    public static final int THAI_MENU_DISH_3_ID = EURO_MENU_DISH_1_ID +5;

    public static final int RUSS_MENU_DISH_1_ID = EURO_MENU_DISH_1_ID +6;
    public static final int RUSS_MENU_DISH_2_ID = EURO_MENU_DISH_1_ID +7;
    public static final int RUSS_MENU_DISH_3_ID = EURO_MENU_DISH_1_ID +8;

    public static final int EURO_MENU_DISH_4_ID = EURO_MENU_DISH_1_ID +9;
    public static final int EURO_MENU_DISH_5_ID = EURO_MENU_DISH_1_ID +10;
    public static final int EURO_MENU_DISH_6_ID = EURO_MENU_DISH_1_ID +11;

    public static final MenuDish EURO_MENU_DISH_1 = new MenuDish(EURO_MENU_DISH_1_ID, LocalDate.of(2018, 10, 29), EURO_DISH_1, EURO_REST);
    public static final MenuDish EURO_MENU_DISH_2 = new MenuDish(EURO_MENU_DISH_2_ID, LocalDate.of(2018, 10, 29), EURO_DISH_2, EURO_REST);
    public static final MenuDish EURO_MENU_DISH_3 = new MenuDish(EURO_MENU_DISH_3_ID, LocalDate.of(2018, 10, 29), EURO_DISH_3, EURO_REST);

    public static final MenuDish THAI_MENU_DISH_1 = new MenuDish(THAI_MENU_DISH_1_ID, LocalDate.of(2018, 10, 30), THAI_DISH_1, THAI_REST);
    public static final MenuDish THAI_MENU_DISH_2 = new MenuDish(THAI_MENU_DISH_2_ID, LocalDate.of(2018, 10, 30), THAI_DISH_2, THAI_REST);
    public static final MenuDish THAI_MENU_DISH_3 = new MenuDish(THAI_MENU_DISH_3_ID, LocalDate.of(2018, 10, 30), THAI_DISH_3, THAI_REST);

    public static final MenuDish RUSS_MENU_DISH_1 = new MenuDish(RUSS_MENU_DISH_1_ID, LocalDate.of(2018, 10, 31), RUSS_DISH_1, RUSS_REST);
    public static final MenuDish RUSS_MENU_DISH_2 = new MenuDish(RUSS_MENU_DISH_2_ID, LocalDate.of(2018, 10, 31), RUSS_DISH_2, RUSS_REST);
    public static final MenuDish RUSS_MENU_DISH_3 = new MenuDish(RUSS_MENU_DISH_3_ID, LocalDate.of(2018, 10, 31), RUSS_DISH_3, RUSS_REST);

    public static final MenuDish EURO_MENU_DISH_4 = new MenuDish(EURO_MENU_DISH_4_ID, LocalDate.of(2018, 10, 31), EURO_DISH_4, EURO_REST);
    public static final MenuDish EURO_MENU_DISH_5 = new MenuDish(EURO_MENU_DISH_5_ID, LocalDate.of(2018, 10, 31), EURO_DISH_5, EURO_REST);
    public static final MenuDish EURO_MENU_DISH_6 = new MenuDish(EURO_MENU_DISH_6_ID, LocalDate.of(2018, 10, 31), EURO_DISH_6, EURO_REST);
    public static final List<MenuDish> EURO_MENU_2018_10_29 = List.of(EURO_MENU_DISH_1, EURO_MENU_DISH_2, EURO_MENU_DISH_3);


    public static final MenuDish CREATED = new MenuDish(EURO_MENU_DISH_6_ID+1, LocalDate.of(2019, 1, 1), EURO_DISH_1, EURO_REST);
    public static final MenuDish UPDATED = new MenuDish(EURO_MENU_DISH_1_ID, LocalDate.of(2019, 1, 1), EURO_DISH_1, EURO_REST);

    public static final List<MenuDish> ALL_MENU = List.of(EURO_MENU_DISH_1, EURO_MENU_DISH_2, EURO_MENU_DISH_3,
                                                          THAI_MENU_DISH_1, THAI_MENU_DISH_2, THAI_MENU_DISH_3,
                                                          RUSS_MENU_DISH_1, RUSS_MENU_DISH_2, RUSS_MENU_DISH_3,
                                                          EURO_MENU_DISH_4, EURO_MENU_DISH_5, EURO_MENU_DISH_6);

    public static final List<MenuDish> ALL_MENU_AFTER_DELETE = List.of(EURO_MENU_DISH_2, EURO_MENU_DISH_3,
                                                          THAI_MENU_DISH_1, THAI_MENU_DISH_2, THAI_MENU_DISH_3,
                                                          RUSS_MENU_DISH_1, RUSS_MENU_DISH_2, RUSS_MENU_DISH_3,
                                                          EURO_MENU_DISH_4, EURO_MENU_DISH_5, EURO_MENU_DISH_6);

    public static final List<MenuDish> ALL_MENU_AFTER_CREATE = List.of(EURO_MENU_DISH_1, EURO_MENU_DISH_2, EURO_MENU_DISH_3,
                                                                        THAI_MENU_DISH_1, THAI_MENU_DISH_2, THAI_MENU_DISH_3,
                                                                        RUSS_MENU_DISH_1, RUSS_MENU_DISH_2, RUSS_MENU_DISH_3,
                                                                        EURO_MENU_DISH_4, EURO_MENU_DISH_5, EURO_MENU_DISH_6,
                                                                        CREATED);

    public static MenuDishTo getUpdatedTo() {
        return new MenuDishTo(EURO_MENU_DISH_1_ID, LocalDate.of(2019, 1, 1), 100000, 100000);
    }

    public static MenuDishTo getCreatedTo() {
        return new MenuDishTo(null, LocalDate.of(2019, 1, 1), 100000, 100000);
    }

    public static void assertMatch(MenuDish actual, MenuDish expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<MenuDish> actual, Iterable<MenuDish> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static ResultMatcher getMenuDishesMatcher(Iterable<MenuDish> expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, MenuDish.class), expected);
    }

    public static ResultMatcher getMenuDishMatcher(MenuDish expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, MenuDish.class), expected);
    }

}
