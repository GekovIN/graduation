package ru.gekov;

import ru.gekov.model.Meal;
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

    public static final Meal EURO_MEAL_1 = new Meal(EURO_MEAL_1_ID, LocalDate.of(2018, 10, 29), "Салат Цезарь", new BigDecimal(500), EURO_REST);
    public static final Meal EURO_MEAL_2 = new Meal(EURO_MEAL_2_ID, LocalDate.of(2018, 10, 29), "Грибной суп", new BigDecimal(1200), EURO_REST);
    public static final Meal EURO_MEAL_3 = new Meal(EURO_MEAL_3_ID, LocalDate.of(2018, 10, 29), "Стейк", new BigDecimal(2100), EURO_REST);
    public static final List<Meal> EURO_MEALS_2018_10_29 = List.of(EURO_MEAL_1, EURO_MEAL_2, EURO_MEAL_3);
    public static final Meal CHANGED_MEAL = new Meal(EURO_MEAL_1_ID, LocalDate.of(2018, 10, 29), "Changed description", new BigDecimal(500), EURO_REST);
    public static final Meal CREATED_MEAL = new Meal(EURO_MEAL_1_ID+9, LocalDate.of(2018, 11, 1), "New Meal", new BigDecimal(10000), EURO_REST);

    public static final Meal THAI_MEAL_1 = new Meal(THAI_MEAL_1_ID, LocalDate.of(2018, 10, 30), "Салат из морепродуктов", new BigDecimal(1100), THAI_REST);
    public static final Meal THAI_MEAL_2 = new Meal(THAI_MEAL_2_ID, LocalDate.of(2018, 10, 30), "Суп том-ям", new BigDecimal(530), THAI_REST);
    public static final Meal THAI_MEAL_3 = new Meal(THAI_MEAL_3_ID, LocalDate.of(2018, 10, 30), "Лапша с курицей", new BigDecimal(510), THAI_REST);
    public static final List<Meal> THAI_MEALS_2018_10_30 = List.of(THAI_MEAL_1, THAI_MEAL_2, THAI_MEAL_3);

    public static final Meal RUSS_MEAL_1 = new Meal(RUSS_MEAL_1_ID, LocalDate.of(2018, 10, 31), "Салат оливье", new BigDecimal(320), RUSS_REST);
    public static final Meal RUSS_MEAL_2 = new Meal(RUSS_MEAL_2_ID, LocalDate.of(2018, 10, 31), "Борщ", new BigDecimal(550), RUSS_REST);
    public static final Meal RUSS_MEAL_3 = new Meal(RUSS_MEAL_3_ID, LocalDate.of(2018, 10, 31), "Котлеты с картофелем", new BigDecimal(700), RUSS_REST);
    public static final List<Meal> RUSS_MEALS_2018_10_31 = List.of(RUSS_MEAL_1, RUSS_MEAL_2, RUSS_MEAL_3);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

}
