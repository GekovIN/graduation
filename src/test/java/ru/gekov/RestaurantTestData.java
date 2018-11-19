package ru.gekov;

import ru.gekov.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final Integer EURO_REST_ID = 100000;
    public static final Integer THAI_REST_ID = 100001;
    public static final Integer RUSS_REST_ID = 100002;

    public static final Restaurant EURO_REST = new Restaurant(EURO_REST_ID, "Европейский ресторан", "Ленинский пр-т, д. 1");
    public static final Restaurant THAI_REST = new Restaurant(THAI_REST_ID, "Тайский ресторан", "Тверская ул., д. 10");
    public static final Restaurant RUSS_REST = new Restaurant(RUSS_REST_ID, "Русский ресторан", "Площадь им. Ленина, д. 18");

    public static final List<Restaurant> ALL_RESTAURANTS = List.of(EURO_REST, THAI_REST, RUSS_REST);
    public static final List<Restaurant> ALL_RESTAURANTS_EXCEPT_FIRST = List.of(THAI_REST, RUSS_REST);

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menuDishes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menuDishes").isEqualTo(expected);
    }

}
