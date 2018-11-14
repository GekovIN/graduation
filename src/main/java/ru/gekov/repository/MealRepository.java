package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByDate(LocalDate date);
    List<Meal> findAllByRestaurantId(int restaurant_id);
    List<Meal> findAllByDateAndRestaurantId(LocalDate date, int id);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM Meal m WHERE m.id=:id")
//    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=?1")
    int delete(int id);

}
