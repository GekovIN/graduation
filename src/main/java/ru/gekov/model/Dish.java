package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "DISHES")
public class Dish extends AbstractNamedEntity {

    @Id
    @SequenceGenerator(name = "DISHES_SEQ", sequenceName = "DISHES_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISHES_SEQ")
    private Integer id;

    @Column(name = "COST")
    @NotNull
    private BigDecimal cost;

    public Dish() {
    }

    public Dish(Integer id, String name, @NotNull BigDecimal cost) {
        super(name);
        this.id = id;
        this.cost = cost;
    }

    public Dish(String name, @NotNull BigDecimal cost) {
        super(name);
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "cost=" + cost +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
