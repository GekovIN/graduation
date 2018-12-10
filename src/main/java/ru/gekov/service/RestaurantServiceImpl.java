package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.gekov.util.ValidationUtil.checkNew;
import static ru.gekov.util.ValidationUtil.checkNotFoundWithId;
import static ru.gekov.util.ValidationUtil.checkNotFoundWithIdAndDate;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Restaurant get(Integer id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public Restaurant getWithMenuDishesById(int id) {
        return checkNotFoundWithId(repository.findByIdWithMenuDishes(id), id);
    }

    @Override
    public Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date) {
        return checkNotFoundWithIdAndDate(repository.findByIdAndDateWithMenuDishes(id, date), id, date);
    }

    @Override
    public List<Restaurant> getWithMenuDishesByDate(LocalDate date) {
        return repository.findByMenuDishesDate(date);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        checkNew(restaurant);
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
