package ru.gekov.to;

import ru.gekov.model.Restaurant;

import java.time.LocalDate;

public class RestaurantVoteCountTo extends AbstractTo {

    private LocalDate date;
    private Restaurant restaurant;
    private Long votesCount;

    public RestaurantVoteCountTo(LocalDate date, Restaurant restaurant, Long votesCount) {
        this.date = date;
        this.restaurant = restaurant;
        this.votesCount = votesCount;
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

    public Long getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Long votesCount) {
        this.votesCount = votesCount;
    }
}