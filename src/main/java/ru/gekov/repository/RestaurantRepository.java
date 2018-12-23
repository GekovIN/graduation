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
            "LEFT JOIN FETCH m.dish")
    List<Restaurant> findAllWithMenuDishes();

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user")
    List<Restaurant> findAllWithVotes();

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish " +
            "LEFT JOIN FETCH r.votes v " +
            "LEFT JOIN FETCH v.user")
    List<Restaurant> findAllWithMenuDishesAndVotes();

    @Transactional
    @Query( "SELECT r FROM Restaurant r " +
            "LEFT JOIN FETCH r.menuDishes m " +
            "LEFT JOIN FETCH m.dish d " +
            "WHERE r.id=?1" )
    Restaurant findByIdWithMenuDishes(int id);

    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN r.menuDishes m WHERE m.date=?1")
    List<Restaurant> findByDate(LocalDate date);
//
//    @Transactional
//    @Query( "SELECT r FROM Restaurant r " +
//            "LEFT JOIN FETCH r.menuDishes m " +
//            "LEFT JOIN FETCH m.dish d " +
//            "WHERE r.id=?1 AND m.date=?2" )
//    Restaurant findByIdAndDateWithMenuDishes(int id, LocalDate date);
//
//    @Transactional
//    @Query( "SELECT DISTINCT r FROM Restaurant r " +
//            "JOIN FETCH r.menuDishes m " +
//            "JOIN FETCH m.dish WHERE m.date=?1" )
//    List<Restaurant> findByMenuDishesDate(LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=?1")
    int delete(int id);
}
