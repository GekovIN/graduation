package ru.gekov.to;

import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DateMenuTo {

    private LocalDate date;
    private Restaurant restaurant;
    private List<MenuDish> dishes;

    public DateMenuTo(LocalDate date) {
        this.date = date;
    }

    public DateMenuTo(LocalDate date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public DateMenuTo(LocalDate date, List<MenuDish> dishes) {
        this.date = date;
        setDishes(dishes);
    }

    public DateMenuTo(LocalDate date, Restaurant restaurant, List<MenuDish> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
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

    public List<MenuDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<MenuDish> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            this.dishes = Collections.emptyList();
        } else {
            this.dishes = dishes;
        }
    }
}
