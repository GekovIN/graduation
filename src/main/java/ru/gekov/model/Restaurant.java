package ru.gekov.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address")
    @NotBlank
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<MenuDish> menuDishes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public List<MenuDish> getMenuDishes() {
        return menuDishes;
    }

    public void setMenuDishes(List<MenuDish> menuDishes) {
        this.menuDishes = menuDishes;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
