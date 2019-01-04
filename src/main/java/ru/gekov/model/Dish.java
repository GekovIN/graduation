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

    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;

    public Dish() {
    }

    public Dish(Integer id, String name, @NotNull BigDecimal price) {
        super(name);
        this.id = id;
        this.price = price;
    }

    public Dish(String name, @NotNull BigDecimal price) {
        super(name);
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                "price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
