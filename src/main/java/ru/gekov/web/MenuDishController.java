package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.MenuDish;
import ru.gekov.service.MenuDishService;
import ru.gekov.to.MenuDishTo;
import ru.gekov.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(MenuDishController.REST_URL)
public class MenuDishController {

    static final String REST_URL = "/restaurants/menus";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuDishService menuService;

    @Autowired
    public MenuDishController(MenuDishService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuDish> getAllWithRestaurants() {
        log.info("get all menuDishes");
        return menuService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuDish get(@PathVariable int id) {
        log.info("get menuDish {}", id);
        return menuService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menuDish {}", id);
        menuService.delete(id);
    }

    @PostMapping
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
