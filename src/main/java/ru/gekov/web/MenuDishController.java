package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.MenuDish;
import ru.gekov.service.MenuDishService;
import ru.gekov.to.MenuDishTo;
import ru.gekov.util.ValidationUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.*;

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
    public ResponseEntity<String> createOrUpdate(@Valid MenuDishTo menuTo, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.processBindingErrors(result);
        }

        if (menuTo.isNew()) {
            log.info("create new menuDish");
            menuService.create(menuTo);
        } else {
            log.info("update menuDish with id=", menuTo.getId());
            menuService.update(menuTo);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
