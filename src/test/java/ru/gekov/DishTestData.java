package ru.gekov;

import ru.gekov.model.Dish;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {

    public static final Integer EURO_DISH_1_ID = 100000;
    public static final Integer EURO_DISH_2_ID = EURO_DISH_1_ID +1;
    public static final Integer EURO_DISH_3_ID = EURO_DISH_1_ID +2;

    public static final Integer THAI_DISH_1_ID = EURO_DISH_1_ID +3;
    public static final Integer THAI_DISH_2_ID = EURO_DISH_1_ID +4;
    public static final Integer THAI_DISH_3_ID = EURO_DISH_1_ID +5;

    public static final Integer RUSS_DISH_1_ID = EURO_DISH_1_ID +6;
    public static final Integer RUSS_DISH_2_ID = EURO_DISH_1_ID +7;
    public static final Integer RUSS_DISH_3_ID = EURO_DISH_1_ID +8;

    public static final Integer EURO_DISH_4_ID = EURO_DISH_1_ID +9;
    public static final Integer EURO_DISH_5_ID = EURO_DISH_1_ID +10;
    public static final Integer EURO_DISH_6_ID = EURO_DISH_1_ID +11;

    public static final Dish EURO_DISH_1 = new Dish(EURO_DISH_1_ID, "Салат Цезарь", new BigDecimal(500));
    public static final Dish EURO_DISH_2 = new Dish(EURO_DISH_2_ID, "Грибной суп", new BigDecimal(1200));
    public static final Dish EURO_DISH_3 = new Dish(EURO_DISH_3_ID, "Стейк", new BigDecimal(2100));

    public static final Dish THAI_DISH_1 = new Dish(THAI_DISH_1_ID, "Салат из морепродуктов", new BigDecimal(1100));
    public static final Dish THAI_DISH_2 = new Dish(THAI_DISH_2_ID, "Суп том-ям", new BigDecimal(530));
    public static final Dish THAI_DISH_3 = new Dish(THAI_DISH_3_ID, "Лапша с курицей", new BigDecimal(2100));

    public static final Dish RUSS_DISH_1 = new Dish(RUSS_DISH_1_ID, "Салат оливье", new BigDecimal(320));
    public static final Dish RUSS_DISH_2 = new Dish(RUSS_DISH_2_ID, "Борщ", new BigDecimal(550));
    public static final Dish RUSS_DISH_3 = new Dish(RUSS_DISH_3_ID, "Котлеты с картофелем", new BigDecimal(700));

    public static final Dish EURO_DISH_4 = new Dish(EURO_DISH_4_ID, "Пицца", new BigDecimal(300));
    public static final Dish EURO_DISH_5 = new Dish(EURO_DISH_5_ID, "Томатный суп", new BigDecimal(270));
    public static final Dish EURO_DISH_6 = new Dish(EURO_DISH_6_ID, "Паста", new BigDecimal(290));

    public static final List<Dish> ALL_DISHES = List.of(EURO_DISH_1, EURO_DISH_2, EURO_DISH_3,
                                                        THAI_DISH_1, THAI_DISH_2,THAI_DISH_3,
                                                        RUSS_DISH_1, RUSS_DISH_2, RUSS_DISH_3);

    public static final List<Dish> ALL_DISHES_EXCEPT_FIRST = List.of(EURO_DISH_2, EURO_DISH_3,
                                                        THAI_DISH_1, THAI_DISH_2,THAI_DISH_3,
                                                        RUSS_DISH_1, RUSS_DISH_2, RUSS_DISH_3);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
