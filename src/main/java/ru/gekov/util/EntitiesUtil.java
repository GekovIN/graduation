package ru.gekov.util;

import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntitiesUtil {

    public static List<Restaurant> getRestaurantsWithMenu(List<MenuDish> menuDishes) {
        Map<Restaurant, List<MenuDish>> dishMap = new HashMap<>();
        menuDishes.forEach(
                m -> dishMap
                        .computeIfAbsent(m.getRestaurant(), d -> new ArrayList<>())
                        .add(m)
        );
        return dishMap.entrySet()
                .stream()
                .map(e -> new Restaurant(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public static Restaurant getRestaurantWithMenu(Restaurant restaurant, List<MenuDish> menuDishes, int restaurantId) {
//      Check that found restaurant id is matching required:
        restaurant = ValidationUtil.checkIdMatch(new Restaurant(restaurant), restaurantId);
        restaurant.setMenuDishes(menuDishes);
        return restaurant;
    }
}
