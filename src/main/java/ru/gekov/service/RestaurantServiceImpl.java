package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.MenuDish;
import ru.gekov.model.Restaurant;
import ru.gekov.model.Vote;
import ru.gekov.repository.MenuDishRepository;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.repository.VoteRepository;
import ru.gekov.to.RestaurantVoteCountTo;
import ru.gekov.util.EntitiesUtil;
import ru.gekov.util.ValidationUtil;

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
    public List<Restaurant> getAllWithMenuDishes() {
        return restaurantRepository.findAllWithMenuDishes();
    }

    @Override
    public List<Restaurant> getAllWithVotes() {
        return restaurantRepository.findAllWithVotes();
    }

    @Override
    public List<Restaurant> getAllWithMenuDishesAndVotes() {
        return restaurantRepository.findAllWithMenuDishesAndVotes();
    }

    @Override
    public Restaurant get(Integer id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public Restaurant getWithMenuDishesById(int id) {
        return checkNotFoundWithId(restaurantRepository.findByIdWithMenuDishes(id), id);
    }

    @Override
    public Restaurant getWithVotesById(int id) {
        return checkNotFoundWithId(restaurantRepository.findByIdWithVotes(id), id);
    }

    @Override
    public Restaurant getWithMenuDishesAndVotesById(int id) {
        return checkNotFoundWithId(restaurantRepository.findByIdWithMenuDishesAndVotes(id), id);
    }

//    Not using join fetch part of child collection in one Select query to
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
    public Restaurant getWithVotesByIdAndDate(int id, LocalDate date) {
        List<Vote> votes = voteRepository.findByRestaurantIdAndDate(id, date);
        if (!votes.isEmpty()) {
            return EntitiesUtil.getRestaurantWithVotes(votes.get(0).getRestaurant(), votes, id);
        }
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById((id)).orElse(null), id);
        return EntitiesUtil.getRestaurantWithVotes(restaurant, Collections.emptyList(), id);
    }

    @Override
    public RestaurantVoteCountTo getWithVotesCountByIdAndDate(int id, LocalDate date) {
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.findById((id)).orElse(null), id);
        Long voteCount = voteRepository.countAllByRestaurantIdAndDate(id, date);
        return new RestaurantVoteCountTo(date, restaurant, voteCount);
    }

    @Override
    public Restaurant getWithMenuDishesAndVotesByIdAndDate(int id, LocalDate date) {
        Restaurant restaurant = null;

        List<MenuDish> menuDishes = menuRepository.findAllByDateAndRestaurantId(date, id);
        if (!menuDishes.isEmpty()) {
            restaurant = menuDishes.get(0).getRestaurant();
        } else {
            menuDishes = Collections.emptyList();
        }

        List<Vote> votes = voteRepository.findByRestaurantIdAndDate(id, date);
        if (!votes.isEmpty() && restaurant == null) {
            restaurant = votes.get(0).getRestaurant();
        } else if (votes.isEmpty()) {
            votes = Collections.emptyList();
        }

        if (restaurant == null) {
            restaurant = checkNotFoundWithId(restaurantRepository.findById((id)).orElse(null), id);
        } else {
            ValidationUtil.checkIdMatch(restaurant, id);
        }

        return EntitiesUtil.getRestaurantWithMenuAndVotes(restaurant, menuDishes, votes, id);
    }

    @Override
    public List<Restaurant> getWithMenuDishesByDate(LocalDate date) {
        List<MenuDish> menuDishes = menuRepository.findAllByDate(date);

        if (menuDishes.isEmpty())
            return Collections.emptyList();

        return EntitiesUtil.getRestaurantsWithMenu(menuDishes);
    }

    @Override
    public List<Restaurant> getWithVotesByDate(LocalDate date) {
        List<Vote> votes = voteRepository.findAllByDate(date);
        if (votes.isEmpty())
            return Collections.emptyList();

        return EntitiesUtil.getRestaurantsWithVotes(votes);
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
