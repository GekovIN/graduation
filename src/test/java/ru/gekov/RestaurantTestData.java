package ru.gekov;

import ru.gekov.model.Restaurant;

public class RestaurantTestData {

    public static final Integer EURO_REST_ID = 100004;
    public static final Integer THAI_REST_ID = 100005;
    public static final Integer RUSS_REST_ID = 100006;

    public static final Restaurant EURO_REST = new Restaurant(EURO_REST_ID, "Европейский ресторан", "Ленинский пр-т, д. 1");
    public static final Restaurant THAI_REST = new Restaurant(THAI_REST_ID, "Тайский ресторан", "Тверская ул., д. 10");
    public static final Restaurant RUSS_REST = new Restaurant(RUSS_REST_ID, "Русский ресторан", "Площадь им. Ленина, д. 18");

}
