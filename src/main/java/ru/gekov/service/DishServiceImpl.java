package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.Dish;
import ru.gekov.repository.DishRepository;

import java.util.List;

import static ru.gekov.util.ValidationUtil.*;

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
    public Dish get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public Dish create(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        checkNew(dish);
        return checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @Override
    public Dish update(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        return repository.save(dish);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
