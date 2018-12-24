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
import ru.gekov.to.RestaurantVoteCountTo;
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

    @GetMapping(value = "/menus-and-votes")
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    public List<Restaurant> getAllWithMenuDishesAndVotes() {
        log.info("get all restaurants with menuDishes and votes");
        return service.getAllWithMenuDishesAndVotes();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable Integer id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    @GetMapping(value = "/{id}/menus", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getByIdWithAllMenus(@PathVariable Integer id) {
        log.info("get restaurant {} with menuDishes", id);
        return service.getWithMenuDishesById(id);
    }

    @GetMapping(value = "/{id}/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    public Restaurant getByIdWithAllVotes(@PathVariable Integer id) {
        log.info("get restaurant {} with votes", id);
        return service.getWithVotesById(id);
    }

    @GetMapping(value = "/{id}/menus-and-votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    public Restaurant getByIdWithMenusAndVotes(@PathVariable Integer id) {
        log.info("get restaurant {} with menus and votes", id);
        return service.getWithMenuDishesAndVotesById(id);
    }

    //Get restaurants (without menuDishes) that have menu for date
    @GetMapping(value = "/haveMenu", params = "date", produces = APPLICATION_JSON_VALUE)
    public List<Restaurant> getHaveMenuByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants that have menu by date {}", date);
        return service.getByDate(date);
    }

    @GetMapping(value = "/menus", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public List<Restaurant> getAllWithMenuByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants with menuDishes by date {}", date);
        return service.getWithMenuDishesByDate(date);
    }

    @GetMapping(value = "/votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    public List<Restaurant> getAllWithVotesByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants with votes by date {}", date);
        return service.getWithVotesByDate(date);
    }

    @GetMapping(value = "/{id}/menus", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getWithMenuByIdAndDate(@PathVariable("id") Integer id,
                                             @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant {} with menuDishes by date {}", id, date);
        return service.getWithMenuDishesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    public Restaurant getWithVotesByIdAndDate(@PathVariable Integer id,
                                              @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant {} with votes by date {}", id, date);
        return service.getWithVotesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/menus-and-votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    public Restaurant getWithMenuAndVotesByIdAndDate(@PathVariable Integer id,
                                                     @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant {} with menuDishes and votes by date {}", id, date);
        return service.getWithMenuDishesAndVotesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/votes-count", params = "date", produces = APPLICATION_JSON_VALUE)
    public RestaurantVoteCountTo getWithVotesCountByIdAndDate(@PathVariable Integer id,
                                                              @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        return service.getWithVotesCountByIdAndDate(id, date);
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
