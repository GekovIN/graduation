package ru.gekov.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Restaurant;
import ru.gekov.repository.RestaurantRepository;

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
        return null;
    }

    @Override
    public Restaurant get(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Restaurant getWithMeals(Integer id) {
        return repository.findByIdWithMeals(id);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant simpleGet(Integer id) {
        Restaurant restaurant = repository.simpleGet(id);
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


}
