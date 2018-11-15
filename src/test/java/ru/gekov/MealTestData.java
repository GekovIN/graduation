package ru.gekov;

import ru.gekov.model.MenuDish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.RestaurantTestData.EURO_REST;
import static ru.gekov.RestaurantTestData.RUSS_REST;
import static ru.gekov.RestaurantTestData.THAI_REST;

public class MealTestData {

    public static final Integer EURO_MEAL_1_ID = 100007;
    public static final Integer EURO_MEAL_2_ID = EURO_MEAL_1_ID+1;
    public static final Integer EURO_MEAL_3_ID = EURO_MEAL_1_ID+2;

    public static final Integer THAI_MEAL_1_ID = EURO_MEAL_1_ID+3;
    public static final Integer THAI_MEAL_2_ID = EURO_MEAL_1_ID+4;
    public static final Integer THAI_MEAL_3_ID = EURO_MEAL_1_ID+5;

    public static final Integer RUSS_MEAL_1_ID = EURO_MEAL_1_ID+6;
    public static final Integer RUSS_MEAL_2_ID = EURO_MEAL_1_ID+7;
    public static final Integer RUSS_MEAL_3_ID = EURO_MEAL_1_ID+8;

    public static final MenuDish EURO_MENU_DISHES_1 = new MenuDish(EURO_MEAL_1_ID, LocalDate.of(2018, 10, 29), "Салат Цезарь", new BigDecimal(500), EURO_REST);
    public static final MenuDish EURO_MENU_DISHES_2 = new MenuDish(EURO_MEAL_2_ID, LocalDate.of(2018, 10, 29), "Грибной суп", new BigDecimal(1200), EURO_REST);
    public static final MenuDish EURO_MENU_DISHES_3 = new MenuDish(EURO_MEAL_3_ID, LocalDate.of(2018, 10, 29), "Стейк", new BigDecimal(2100), EURO_REST);
    public static final List<MenuDish> EURO_MEALS_20181029 = List.of(EURO_MENU_DISHES_1, EURO_MENU_DISHES_2, EURO_MENU_DISHES_3);
    public static final MenuDish CHANGED_MENU_DISHES = new MenuDish(EURO_MEAL_1_ID, LocalDate.of(2018, 10, 29), "Changed description", new BigDecimal(500), EURO_REST);
    public static final MenuDish CREATED_MENU_DISHES = new MenuDish(EURO_MEAL_1_ID+9, LocalDate.of(2018, 11, 1), "New MenuDish", new BigDecimal(10000), EURO_REST);

    public static final MenuDish THAI_MENU_DISHES_1 = new MenuDish(THAI_MEAL_1_ID, LocalDate.of(2018, 10, 30), "Салат из морепродуктов", new BigDecimal(1100), THAI_REST);
    public static final MenuDish THAI_MENU_DISHES_2 = new MenuDish(THAI_MEAL_2_ID, LocalDate.of(2018, 10, 30), "Суп том-ям", new BigDecimal(530), THAI_REST);
    public static final MenuDish THAI_MENU_DISHES_3 = new MenuDish(THAI_MEAL_3_ID, LocalDate.of(2018, 10, 30), "Лапша с курицей", new BigDecimal(510), THAI_REST);
    public static final List<MenuDish> THAI_MEALS_20181030 = List.of(THAI_MENU_DISHES_1, THAI_MENU_DISHES_2, THAI_MENU_DISHES_3);

    public static final MenuDish RUSS_MENU_DISHES_1 = new MenuDish(RUSS_MEAL_1_ID, LocalDate.of(2018, 10, 31), "Салат оливье", new BigDecimal(320), RUSS_REST);
    public static final MenuDish RUSS_MENU_DISHES_2 = new MenuDish(RUSS_MEAL_2_ID, LocalDate.of(2018, 10, 31), "Борщ", new BigDecimal(550), RUSS_REST);
    public static final MenuDish RUSS_MENU_DISHES_3 = new MenuDish(RUSS_MEAL_3_ID, LocalDate.of(2018, 10, 31), "Котлеты с картофелем", new BigDecimal(700), RUSS_REST);
    public static final List<MenuDish> RUSS_MEALS_20181031 = List.of(RUSS_MENU_DISHES_1, RUSS_MENU_DISHES_2, RUSS_MENU_DISHES_3);


    public static void assertMatch(MenuDish actual, MenuDish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<MenuDish> actual, Iterable<MenuDish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

}
