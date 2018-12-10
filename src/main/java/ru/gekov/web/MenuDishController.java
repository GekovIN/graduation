package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.service.MenuDishService;
import ru.gekov.service.RestaurantService;
import ru.gekov.to.DateMenuTo;
import ru.gekov.util.MenuDishUtil;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(MenuDishController.REST_URL)
public class MenuDishController {

    static final String REST_URL = "/menus";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuDishService menuService;
    private final RestaurantService restaurantService;

    @Autowired
    public MenuDishController(MenuDishService menuService, RestaurantService restaurantService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
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

    @GetMapping(path = "/by",
                params = "date",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DateMenuTo> getByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                    @RequestParam("date") LocalDate date) {

        log.info("get menuDishes by date {}", date);
        List<MenuDish> menuDishes = menuService.getAllByDate(date);
        return MenuDishUtil.getDateMenus(date, menuDishes);
    }

    @GetMapping(path = "/by",
                params = {"date", "restaurantId"},
                produces = MediaType.APPLICATION_JSON_VALUE)
    public DateMenuTo getByDateAndRestaurantId(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               @RequestParam("date") LocalDate date,
                                               @RequestParam("restaurantId") int restaurantId) {

        log.info("get menuDishes by date {} and restaurant {}", date, restaurantId);
        List<MenuDish> menuDishes = menuService.getByDateAndRestaurantId(date, restaurantId);
        Restaurant restaurant;
        if (menuDishes.isEmpty()) {
            restaurant = restaurantService.get(restaurantId);
        } else {
            restaurant = menuDishes.get(0).getRestaurant();
        }
        return MenuDishUtil.getRestaurantDateMenu(date, restaurant, menuDishes);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete menuDish {}", id);
        menuService.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam("dishId") Integer dishId,
                               @RequestParam("restaurantId") Integer restaurantId) {
        MenuDish menuDish = new MenuDish(id, date);
        if (menuDish.isNew()) {
            log.info("create menuDish");
            menuService.create(menuDish, dishId, restaurantId);
        }

        //TODO Update
    }
}
