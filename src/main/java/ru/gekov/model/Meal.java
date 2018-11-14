package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "MEALS", uniqueConstraints = {@UniqueConstraint(columnNames = {"RESTAURANT_ID", "DATE"}, name = "meals_date_idx")})
public class Meal extends AbstractBaseEntity {

    @Column(name = "DATE")
    @NotNull
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    @NotBlank
    private String description;

    @Column(name = "AMOUNT")
    @NotNull
    private BigDecimal amount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(@NotNull LocalDate date, @NotBlank String description, @NotNull BigDecimal amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Meal(@NotNull LocalDate date, @NotBlank String description, @NotNull BigDecimal amount, @NotNull Restaurant restaurant) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, @NotNull LocalDate date, @NotBlank String description, @NotNull BigDecimal amount, @NotNull Restaurant restaurant) {
        super(id);
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
