package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gekov.model.MenuDish;
import ru.gekov.service.MenuDishService;
import ru.gekov.to.MenuDishTo;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.*;
import static ru.gekov.util.ValidationUtil.*;

@RestController
@RequestMapping(MenuDishController.REST_URL)
public class MenuDishController {

    static final String REST_URL = "/menus";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuDishService menuService;

    @Autowired
    public MenuDishController(MenuDishService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public List<MenuDish> getAll() {
        log.info("get all menuDishes");
        return menuService.getAll();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public MenuDish get(@PathVariable int id) {
        log.info("get menuDish {}", id);
        return menuService.getById(id);
    }

    @GetMapping(params = "date", produces = APPLICATION_JSON_VALUE)
    public List<MenuDish> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get menuDishes by {}", date);
        return menuService.getAllByDate(date);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable int id) {
        log.info("delete menuDish {}", id);
        menuService.delete(id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MenuDish> createWithNewUri(@Valid @RequestBody MenuDishTo menuTo) {
        log.info("create {}", menuTo);
        checkNew(menuTo);
        MenuDish created = menuService.create(menuTo);
        URI newResourceUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(newResourceUri).body(created);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void update(@Valid @RequestBody MenuDishTo menuTo, @PathVariable Integer id) {
        log.info("update {} with id={}", menuTo, id);
        assureToIdConsistent(menuTo, id);
        menuService.update(menuTo);
    }

}
