package ru.gekov.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import ru.gekov.model.Restaurant;
import ru.gekov.service.RestaurantService;
import ru.gekov.util.ValidationUtil;
import ru.gekov.web.json.View;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.*;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {

    static final String REST_URL = "/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.service = restaurantService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping(value = "/menus", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public List<Restaurant> getAllWithMenuDishes() {
        log.info("get all restaurants with menuDishes");
        return service.getAllWithMenuDishes();
    }

    @GetMapping(value = "/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    public List<Restaurant> getAllWithVotes() {
        log.info("get all restaurants with votes");
        return service.getAllWithVotes();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    @GetMapping(value = "/{id}/menus", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getByIdWithAllMenus(@PathVariable int id) {
        log.info("get restaurant {} with menuDishes");
        return service.getWithMenuDishesById(id);
    }

    //Get restaurants (without menuDishes) that have menu for date
    @GetMapping(value = "/haveMenu", params = "date", produces = APPLICATION_JSON_VALUE)
    public List<Restaurant> getHaveMenuByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants that have menu by date {}", date);
        return service.getByDate(date);
    }

    @GetMapping(params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public List<Restaurant> getAllWithMenuByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants with menuDishes by date {}", date);
        return service.getWithMenuDishesByDate(date);
    }

    @GetMapping(value = "/{id}", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getWithMenuByIdAndDate(@PathVariable("id") Integer id,
                                             @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant {} with menuDishes by date {}", id, date);
        return service.getWithMenuDishesByIdAndDate(id, date);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid Restaurant restaurant, BindingResult result) {

        if (result.hasErrors()) {
            return ValidationUtil.processBindingErrors(result);
        }
        if (restaurant.isNew()) {
            log.info("create new restaurant");
            service.create(restaurant);
        } else {
            log.info("update restaurant {}", restaurant.getId());
            service.update(restaurant);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
