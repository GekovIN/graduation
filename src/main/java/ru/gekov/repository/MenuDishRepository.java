package ru.gekov.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.MenuDish;

import javax.persistence.OrderBy;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuDishRepository extends JpaRepository<MenuDish, Integer> {

    @Override
    @EntityGraph(attributePaths = {"dish", "restaurant"})
    Optional<MenuDish> findById(Integer integer);

    @Override
    @EntityGraph(attributePaths = {"dish", "restaurant"})
    List<MenuDish> findAll();

    @EntityGraph(attributePaths = {"dish", "restaurant"})
    @OrderBy(value = "id")
    List<MenuDish> findAllByDate(LocalDate date);

    @EntityGraph(attributePaths = {"dish"})
    List<MenuDish> findAllByRestaurantId(int restaurant_id);

    @EntityGraph(attributePaths = {"dish", "restaurant"})
    List<MenuDish> findAllByDateAndRestaurantId(LocalDate date, int id);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM MenuDish m WHERE m.id=:id")
//    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDish m WHERE m.id=?1")
    int delete(int id);

}
