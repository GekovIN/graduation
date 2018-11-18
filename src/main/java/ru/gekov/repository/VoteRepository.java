package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findByDate(LocalDate date);
    List<Vote> findByRestaurantIdAndDate(int restaurantId, LocalDate date);
    Optional<Vote> findByUserIdAndDate(int userId, LocalDate date);
    Long countAllByRestaurantAndDate(int restaurantId, LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
