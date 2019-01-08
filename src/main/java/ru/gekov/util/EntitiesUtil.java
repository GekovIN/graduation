package ru.gekov.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.model.User;
import ru.gekov.model.Vote;

import java.util.*;
import java.util.stream.Collectors;

public class EntitiesUtil {

    public static List<Restaurant> getRestaurantsWithMenu(List<MenuDish> menuDishes) {
        Map<Restaurant, List<MenuDish>> dishMap = new LinkedHashMap<>();
        menuDishes.forEach(
                m -> dishMap
                        .computeIfAbsent(m.getRestaurant(), d -> new ArrayList<>())
                        .add(m)
        );
        return dishMap.entrySet()
                .stream()
                .map(e -> new Restaurant(e.getKey(), new LinkedHashSet<>(e.getValue())))
                .collect(Collectors.toList());
    }

    public static Restaurant getRestaurantWithMenu(Restaurant restaurant, List<MenuDish> menuDishes, int restaurantId) {
//      Check that found restaurant id is matching required:
        restaurant = ValidationUtil.checkIdMatch(new Restaurant(restaurant), restaurantId);
        restaurant.setMenuDishes(new LinkedHashSet<>(menuDishes));
        return restaurant;
    }

    public static List<Restaurant> getRestaurantsWithVotes(List<Vote> votes) {
        Map<Restaurant, List<Vote>> votesMap = new LinkedHashMap<>();
        votes.forEach(
                m -> votesMap
                        .computeIfAbsent(m.getRestaurant(), d -> new ArrayList<>())
                        .add(m)
        );

        return votesMap.entrySet()
                .stream()
                .map(e -> {
                    Restaurant restaurant = new Restaurant(e.getKey());
                    restaurant.setVotes(new LinkedHashSet<>(e.getValue()));
                    return restaurant;
                })
                .collect(Collectors.toList());
    }

    public static Restaurant getRestaurantWithVotes(Restaurant restaurant, List<Vote> votes, int restaurantId) {
//      Check that found restaurant id is matching required:
        restaurant = ValidationUtil.checkIdMatch(new Restaurant(restaurant), restaurantId);
        restaurant.setVotes(new LinkedHashSet<>(votes));
        return restaurant;
    }

    public static Restaurant getRestaurantWithMenuAndVotes(Restaurant restaurant, List<MenuDish> menuDishes,
                                                           List<Vote> votes, int restaurantId)  {
        restaurant = ValidationUtil.checkIdMatch(new Restaurant(restaurant), restaurantId);
        restaurant.setMenuDishes(new LinkedHashSet<>(menuDishes));
        restaurant.setVotes(new LinkedHashSet<>(votes));
        return restaurant;
    }

    public static User prepareUserToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
