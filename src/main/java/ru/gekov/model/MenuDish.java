package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "MENU_DISHES", uniqueConstraints = {@UniqueConstraint(columnNames = {"RESTAURANT_ID", "DATE"}, name = "meals_date_idx")})
public class MenuDish extends AbstractBaseEntity {

    @Id
    @SequenceGenerator(name = "MENU_DISHES_SEQ", sequenceName = "MENU_DISHES_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_DISHES_SEQ")
    private Integer id;

    @Column(name = "DATE")
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "DISH_ID")
    @NotNull
    private Dish dish;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "RESTAURANT_ID")
    @NotNull
    private Restaurant restaurant;


    public MenuDish() {
    }

    public MenuDish(@NotNull LocalDate date, Dish dish, @NotNull Restaurant restaurant) {
        this.date = date;
        this.dish = dish;
        this.restaurant = restaurant;
    }

    public MenuDish(Integer id, @NotNull LocalDate date, Dish dish, @NotNull Restaurant restaurant) {
        this.id = id;
        this.date = date;
        this.dish = dish;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MenuDish{" +
                "id=" + id +
                ", date=" + date +
                ", dish=" + dish +
//                ", restaurant=" + restaurant +
                '}';
    }
}
