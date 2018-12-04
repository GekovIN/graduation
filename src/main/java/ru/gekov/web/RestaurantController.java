package ru.gekov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import ru.gekov.model.Restaurant;
import ru.gekov.service.RestaurantService;
import java.util.List;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {

    static final String REST_URL = "/restaurants";
    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.service = restaurantService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getById(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("address") String address) {
        Restaurant restaurant = new Restaurant(id, name, address);
        if (restaurant.isNew()) {
            service.save(restaurant);
        }

        //TODO Update
    }

}
