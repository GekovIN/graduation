package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Meal;
import ru.gekov.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.meals WHERE r.id=:id")
    Restaurant findByIdWithMeals(@Param("id") Integer id);

    @Transactional
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant simpleGet(@Param("id") Integer id);
}
