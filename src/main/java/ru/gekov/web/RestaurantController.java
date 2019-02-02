package ru.gekov.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gekov.model.Restaurant;
import ru.gekov.service.RestaurantService;
import ru.gekov.to.RestaurantVoteCountTo;
import ru.gekov.web.json.View;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.*;
import static org.springframework.http.MediaType.*;
import static ru.gekov.util.ValidationUtil.*;

@RestController
@RequestMapping(RestaurantController.REST_URL)
@CrossOrigin("*")
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
    @Secured("ROLE_ADMIN")
    public List<Restaurant> getAllWithVotes() {
        log.info("get all restaurants with votes");
        return service.getAllWithVotes();
    }

    @GetMapping(value = "/menus-and-votes")
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    @Secured("ROLE_ADMIN")
    public List<Restaurant> getAllWithMenuDishesAndVotes() {
        log.info("get all restaurants with menuDishes and votes");
        return service.getAllWithMenuDishesAndVotes();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable Integer id) {
        log.info("get restaurant with id={}", id);
        return service.get(id);
    }

    @GetMapping(value = "/{id}/menus", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getByIdWithAllMenus(@PathVariable Integer id) {
        log.info("get restaurant with id={} with menuDishes", id);
        return service.getWithMenuDishesById(id);
    }

    @GetMapping(value = "/{id}/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    @Secured("ROLE_ADMIN")
    public Restaurant getByIdWithAllVotes(@PathVariable Integer id) {
        log.info("get restaurant with id={} with votes", id);
        return service.getWithVotesById(id);
    }

    @GetMapping(value = "/{id}/menus-and-votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    @Secured("ROLE_ADMIN")
    public Restaurant getByIdWithMenusAndVotes(@PathVariable Integer id) {
        log.info("get restaurant with id={} with menus and votes", id);
        return service.getWithMenuDishesAndVotesById(id);
    }

    //Get restaurants (without menuDishes) that have menu for date
    @GetMapping(value = "/haveMenu", params = "date", produces = APPLICATION_JSON_VALUE)
    public List<Restaurant> getHaveMenuByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants that have menu by date {}", date);
        return service.getByMenuDate(date);
    }

    @GetMapping(value = "/menus", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public List<Restaurant> getAllWithMenuByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants with menuDishes by date {}", date);
        return service.getWithMenuDishesByDate(date);
    }

    @GetMapping(value = "/votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    @Secured("ROLE_ADMIN")
    public List<Restaurant> getAllWithVotesByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get restaurants with votes by date {}", date);
        return service.getWithVotesByDate(date);
    }

    @GetMapping(value = "/{id}/menus", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenu.class)
    public Restaurant getWithMenuByIdAndDate(@PathVariable("id") Integer id,
                                             @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant with id={} with menuDishes by date {}", id, date);
        return service.getWithMenuDishesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithVote.class)
    @Secured("ROLE_ADMIN")
    public Restaurant getWithVotesByIdAndDate(@PathVariable Integer id,
                                              @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant with id={} with votes by date {}", id, date);
        return service.getWithVotesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/menus-and-votes", params = "date", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonRestaurantsWithMenuAndVotes.class)
    @Secured("ROLE_ADMIN")
    public Restaurant getWithMenuAndVotesByIdAndDate(@PathVariable Integer id,
                                                     @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant with id={} with menuDishes and votes by date {}", id, date);
        return service.getWithMenuDishesAndVotesByIdAndDate(id, date);
    }

    @GetMapping(value = "/{id}/votes-count", params = "date", produces = APPLICATION_JSON_VALUE)
    public RestaurantVoteCountTo getWithVotesCountByIdAndDate(@PathVariable Integer id,
                                                              @DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurant with id={} with votes count by date {}", id, date);
        return service.getWithVotesCountByIdAndDate(id, date);
    }

    @GetMapping(value = "/votes-count", params = "date", produces = APPLICATION_JSON_VALUE)
    public List<RestaurantVoteCountTo> getWithVotesCountByDate(@DateTimeFormat(iso = ISO.DATE) @RequestParam("date") LocalDate date) {
        log.info("get restaurants with votes count by date {}", date);
        return service.getWithVotesCountByDate(date);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable int id) {
        log.info("delete restaurant with id={}", id);
        service.delete(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> createWithNewUri(@Valid @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = service.create(restaurant);
        URI newResourceUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(newResourceUri).body(created);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable Integer id) {
        log.info("update {} with id={}", restaurant, id);
        assureEntityIdConsistent(restaurant, id);
        service.update(restaurant);
    }

}
