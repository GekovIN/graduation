package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "MEALS", uniqueConstraints = {@UniqueConstraint(columnNames = {"RESTAURANT_ID", "DATE"}, name = "meals_date_idx")})
public class Meal extends AbstractBaseEntity {

    @Column(name = "DATE")
    @NotNull
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
