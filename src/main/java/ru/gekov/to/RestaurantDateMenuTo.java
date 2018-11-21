package ru.gekov.to;

import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class RestaurantDateMenuTo {

    private LocalDate date;
    private Restaurant restaurant;
    private List<MenuDish> menuDishes;

    public RestaurantDateMenuTo(LocalDate date, List<MenuDish> menuDishes) {
        this.date = date;
        setMenuDishes(menuDishes);
    }

    public RestaurantDateMenuTo(LocalDate date, Restaurant restaurant, List<MenuDish> menuDishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.menuDishes = menuDishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuDish> getMenuDishes() {
        return menuDishes;
    }

    public void setMenuDishes(List<MenuDish> menuDishes) {
        if (menuDishes == null || menuDishes.isEmpty()) {
            this.menuDishes = Collections.emptyList();
        } else {
            this.menuDishes = menuDishes;
        }
    }
}
