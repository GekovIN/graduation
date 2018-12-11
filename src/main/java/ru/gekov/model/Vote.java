package ru.gekov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "VOTES")
public class Vote extends AbstractBaseEntity {

    @Id
    @SequenceGenerator(name = "VOTES_SEQ", sequenceName = "VOTES_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTES_SEQ")
    private Integer id;

    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @JsonIgnoreProperties("votes")
    @NotNull
    private Restaurant restaurant;


    public Vote() {
    }



    public Vote(LocalDate date, @NotNull User user, @NotNull Restaurant restaurant) {
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDate date, @NotNull User user, @NotNull Restaurant restaurant) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
