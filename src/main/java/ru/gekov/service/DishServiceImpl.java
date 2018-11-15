package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.Dish;
import ru.gekov.repository.DishRepository;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public Dish getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}
