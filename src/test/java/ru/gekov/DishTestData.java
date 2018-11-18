package ru.gekov;

import ru.gekov.model.Dish;

import java.math.BigDecimal;

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

    public static final Dish EURO_DISH_1 = new Dish("Салат цезарь", new BigDecimal(500));
    public static final Dish EURO_DISH_2 = new Dish("Грибной суп", new BigDecimal(1200));
    public static final Dish EURO_DISH_3 = new Dish("Стейк", new BigDecimal(2100));

    public static final Dish THAI_DISH_1 = new Dish("Салат из морепродуктов", new BigDecimal(1100));
    public static final Dish THAI_DISH_2 = new Dish("Суп том-ям", new BigDecimal(530));
    public static final Dish THAI_DISH_3 = new Dish("Лапша с курицей", new BigDecimal(2100));

    public static final Dish RUSS_DISH_1 = new Dish("Салат оливье", new BigDecimal(320));
    public static final Dish RUSS_DISH_2 = new Dish("Борщ", new BigDecimal(550));
    public static final Dish RUSS_DISH_3 = new Dish("Котлеты с картофелем", new BigDecimal(700));

}
