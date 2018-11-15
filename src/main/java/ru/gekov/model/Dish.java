package ru.gekov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "DISHES")
public class Dish extends AbstractNamedEntity {

    @Column(name = "COST")
    @NotNull
    private BigDecimal cost;

    public Dish() {
    }

    public Dish(Integer id, String name, @NotNull BigDecimal cost) {
        super(id, name);
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
