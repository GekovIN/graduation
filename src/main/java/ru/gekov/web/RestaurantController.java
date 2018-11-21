package ru.gekov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gekov.model.Dish;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.service.DishService;
import ru.gekov.service.MenuDishService;
import ru.gekov.service.RestaurantService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuDishService menuDishService;
    private final DishService dishService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, MenuDishService menuDishService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.menuDishService = menuDishService;
        this.dishService = dishService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Restaurant> restaurants = restaurantService.getAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

//    @GetMapping("/{id}}/menu")
//    public String getCurrentDateMenuDish(@PathVariable Integer id, Model model) {
//        List<MenuDish> menuDishes = menuDishService.getAllByDateAndRestaurantId(LocalDate.now(), id);
//        model.addAttribute("menu", menuDishes);
//        return "menu";
//    }

    //Test:
    @GetMapping("/{id}/menu")
    public String getCurrentDateMenuDish(@PathVariable Integer id, Model model) {
        List<MenuDish> menuDishes = menuDishService.getAllByDateAndRestaurantId(LocalDate.of(2018, 10, 29), id);
        model.addAttribute("menu", menuDishes);
        return "menu";
    }

    @GetMapping("/{id}/menu/add")
    public String createForm(Model model, @PathVariable int id) {
        List<Dish> dishes = dishService.getAll();
        model.addAttribute("dishes", dishes);
        Restaurant restaurant = restaurantService.get(id);
        MenuDish menuDish = new MenuDish(LocalDate.now(), restaurant);
        model.addAttribute("menu", menuDish);
        return "menuForm";
    }

    @GetMapping("/menu/{menuId}/update")
    public String updateForm(Model model, @PathVariable int menuId) {
        MenuDish menuDish = menuDishService.getById(menuId);
        model.addAttribute("menu", menuDish);
        List<Dish> dishes = dishService.getAll();
        model.addAttribute("dishes", dishes);
        return "menuForm";
    }

    @PostMapping("/{restId}/menu/save")
    public String save(HttpServletRequest request, @PathVariable int restId) {
        String menuDishId = request.getParameter("id");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int dishId = Integer.parseInt(request.getParameter("selectedDishId"));
        if (menuDishId == null || menuDishId.isEmpty()) {
            menuDishService.create(date, dishId, restId);
        } else {
            MenuDish menuDish = new MenuDish(Integer.parseInt(menuDishId), date);
            menuDishService.update(menuDish, dishId, restId);
        }
        return "redirect:/restaurants/" + restId + "/menu";
    }

    @GetMapping("/{restId}/menu/{menuId}/delete")
    public String delete(@PathVariable int menuId, @PathVariable String restId) {
        menuDishService.delete(menuId);
        return "redirect:/restaurants/" + restId + "/menu";
    }
}
