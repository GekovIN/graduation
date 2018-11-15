package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gekov.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
