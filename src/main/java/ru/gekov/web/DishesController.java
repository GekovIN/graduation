package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.Dish;
import ru.gekov.service.DishService;
import ru.gekov.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(DishesController.REST_URL)
public class DishesController {

    static final String REST_URL = "/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DishService service;

    @Autowired
    public DishesController(DishService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable Integer id) {
        log.info("get dish with id=", id);
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.info("delete dish with id=", id);
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid Dish dish, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.processBindingErrors(result);
        }
        if (dish.isNew()) {
            log.info("create new dish");
            service.create(dish);
        } else {
            log.info("update dish", dish.getId());
            service.update(dish);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
