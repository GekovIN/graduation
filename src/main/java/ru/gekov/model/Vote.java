package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "VOTES")
public class Vote extends AbstractBaseEntity {

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date, @NotNull User user, @NotNull Restaurant restaurant) {
        super(id);
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}