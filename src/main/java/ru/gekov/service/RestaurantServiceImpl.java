package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.repository.VoteRepository;
import ru.gekov.util.EntitiesUtil;

import java.time.LocalDate;
import java.util.*;

import static ru.gekov.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuDishRepository menuRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuDishRepository menuRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant get(Integer id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public Restaurant getWithMenuDishesById(int id) {
        return checkNotFoundWithId(restaurantRepository.findByIdWithMenuDishes(id), id);
    }

//    Not using join fetch part of MenuDish child collection in one Select query to
//    avoid problems with EM cache and because this is not allowed by JPA spec.
//    https://stackoverflow.com/questions/36103442/hibernate-select-parents-with-list-of-childs-matches-child-parameter
//    https://stackoverflow.com/questions/3585488/jpa-fetch-join-filter-on-list-set

    @Override
    public Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date) {
        List<MenuDish> menuDishes = menuRepository.findAllByDateAndRestaurantId(date, id);
        if (!menuDishes.isEmpty()) {
            return EntitiesUtil.getRestaurantWithMenu(menuDishes.get(0).getRestaurant(), menuDishes, id);
        }
//      Find restaurant from repository only if there was no menuDishes by this id and date:
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
        return EntitiesUtil.getRestaurantWithMenu(restaurant, Collections.emptyList(), id);
    }

    @Override
    public List<Restaurant> getWithMenuDishesByDate(LocalDate date) {
        List<MenuDish> menuDishes = menuRepository.findAllByDate(date);

        if (menuDishes.isEmpty())
            return Collections.emptyList();

        return EntitiesUtil.getRestaurantsWithMenu(menuDishes);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Override
    public List<Restaurant> getByDate(LocalDate date) {
        return restaurantRepository.findByDate(date);
    }
}
