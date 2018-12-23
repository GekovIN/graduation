package ru.gekov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import ru.gekov.web.json.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant extends AbstractNamedEntity {

    @Id
    @SequenceGenerator(name = "RESTAURANTS_SEQ", sequenceName = "RESTAURANTS_SEQ",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANTS_SEQ")
    private Integer id;

    @Column(name = "address")
    @NotBlank
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @JsonIgnoreProperties("restaurant")
    @JsonView(View.JsonRestaurantsWithMenu.class)
    private List<MenuDish> menuDishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @JsonIgnoreProperties("restaurant")
    @JsonView(View.JsonRestaurantsWithVote.class)
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address) {
        super(name);
        this.id=id;
        this.address = address;
    }

    public Restaurant(String name, String address) {
        super(name);
        this.address = address;
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getName());
        this.id = restaurant.getId();
        this.address = restaurant.getAddress();
    }

    public Restaurant(Restaurant restaurant, List<MenuDish> menuDishes) {
        super(restaurant.getName());
        this.id = restaurant.getId();
        this.address = restaurant.getAddress();
        setMenuDishes(menuDishes);
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", menuDishes= Lazy Init" +
                '}';
    }
}
