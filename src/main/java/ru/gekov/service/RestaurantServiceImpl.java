package ru.gekov.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

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
        return repository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getWithMenuDishesById(int id) {
        return repository.findByIdWithMenuDishes(id);
    }

    @Override
    public Restaurant getWithMenuDishesByIdAndDate(int id, LocalDate date) {
        return repository.findByIdAndDateWithMenuDishes(id, date);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}
