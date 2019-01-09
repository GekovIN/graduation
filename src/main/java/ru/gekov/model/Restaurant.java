package ru.gekov.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.SafeHtml;
import ru.gekov.web.json.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Set;

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
    @SafeHtml
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")

//  https://stackoverflow.com/questions/40972817/jsonexception-no-valuedeserializer-assigned
    @JsonIgnoreProperties(value = "restaurant", allowSetters = true)
    @JsonView(View.JsonRestaurantsWithMenu.class)
//    https://hibernate.atlassian.net/browse/HHH-1076
//    https://stackoverflow.com/questions/9720452/duplicates-using-left-join-fetch
    private Set<MenuDish> menuDishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")

//  https://stackoverflow.com/questions/40972817/jsonexception-no-valuedeserializer-assigned
    @JsonIgnoreProperties(value = "restaurant", allowSetters = true)
    @JsonView(View.JsonRestaurantsWithVote.class)
//  https://stackoverflow.com/questions/4334970/hibernate-cannot-simultaneously-fetch-multiple-bags
    private Set<Vote> votes;

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

    public Restaurant(Restaurant restaurant, Set<MenuDish> menuDishes) {
        super(restaurant.getName());
        this.id = restaurant.getId();
        this.address = restaurant.getAddress();
        setMenuDishes(menuDishes);
    }

    public String getAddress() {
        return address;
    }

    public Set<MenuDish> getMenuDishes() {
        return menuDishes;
    }

    public void setMenuDishes(Set<MenuDish> menuDishes) {
        if (menuDishes.isEmpty())
            this.menuDishes = Collections.emptySet();
        this.menuDishes = menuDishes;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        if (votes.isEmpty())
            this.votes = Collections.emptySet();
        this.votes = votes;
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
