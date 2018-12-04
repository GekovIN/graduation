package ru.gekov.util;

import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.to.DateMenuTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuDishUtil {

    //Convert list of menu dishes by one date to list of DateMenuTo
    public static List<DateMenuTo> getDateMenus(LocalDate date, List<MenuDish> menuDishes) {
        Map<Restaurant, List<MenuDish>> dishMap = new HashMap<>();
        menuDishes.forEach(m -> {
            dishMap.computeIfAbsent(m.getRestaurant(), d -> new ArrayList<>()).add(m);
            setUnnecessaryFieldsToNull(m);
        });

        return dishMap.entrySet()
                .stream()
                .map(e -> new DateMenuTo(date, e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    //Convert list of menuDishes by date and restaurant in one DateMenuTo obj
    public static DateMenuTo getRestaurantDateMenu(LocalDate date, Restaurant restaurant, List<MenuDish> menuDishes) {
        menuDishes.forEach(MenuDishUtil::setUnnecessaryFieldsToNull);
        return new DateMenuTo(date, restaurant, menuDishes);
    }

    //setting unnecessary for DateMenuTo restaurant and date fields to null (to avoid json mapping)
    private static void setUnnecessaryFieldsToNull(MenuDish m) {
        m.setRestaurant(null);
        m.setDate(null);
    }


}
