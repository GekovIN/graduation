package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Restaurant;

import java.time.LocalDate;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    //Join menuDishes with dishes in one select:
    @Transactional
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuDishes m LEFT JOIN FETCH m.dish WHERE r.id=?1")
    Restaurant findByIdWithMenuDishes(int id);

    @Transactional
    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuDishes m WHERE r.id=?1 AND m.date=?2")
    Restaurant findByIdAndDateWithMenuDishes(int id, LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);
}
