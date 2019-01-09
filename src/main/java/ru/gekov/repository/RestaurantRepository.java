package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish " +
            "ORDER BY r.id")
    List<Restaurant> findAllWithMenuDishes();

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user " +
            "ORDER BY r.id")
    List<Restaurant> findAllWithVotes();

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user " +
            "ORDER BY r.id")
    List<Restaurant> findAllWithMenuDishesAndVotes();

    @Transactional
    @Query( "SELECT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish d " +
            "WHERE r.id=?1 " +
            "ORDER BY r.id" )
    Restaurant findByIdWithMenuDishes(int id);

    @Transactional
    @Query( "SELECT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user " +
            "WHERE r.id=?1 " +
            "ORDER BY r.id" )
    Restaurant findByIdWithVotes(int id);

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user WHERE r.id=?1 " +
            "ORDER BY r.id")
    Restaurant findByIdWithMenuDishesAndVotes(int id);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN r.menuDishes m WHERE m.date=?1 ORDER BY r.id")
    List<Restaurant> findByDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);
}
