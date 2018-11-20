package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.Vote;
import ru.gekov.util.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.RestaurantTestData.*;
import static ru.gekov.UserTestData.*;
import static ru.gekov.VoteTestData.*;

public class VoteServiceImplTest extends ServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void get() {
        Vote vote = service.get(VOTE_1_ID);
        assertMatch(vote, VOTE_1);
    }

    @Test
    public void getByDate() {
        List<Vote> votes = service.getByDate(LocalDate.of(2018, 10, 29));
        assertMatch(votes, ALL_VOTES);
    }

    @Test
    public void getByRestaurantAndDate() {
        List<Vote> votes = service.getByRestaurantAndDate(EURO_REST_ID, LocalDate.of(2018, 10, 29));
        assertMatch(votes, List.of(VOTE_1, VOTE_2));
    }

    @Test
    public void getByUserAndDate() {
        Vote vote = service.getByUserAndDate(USER_1_ID, LocalDate.of(2018, 10, 29));
        assertMatch(vote, VOTE_1);
    }

    @Test
    public void create() {
        service.save(LocalDateTime.of(2018, 1, 1, 10, 0), USER_3_ID, RUSS_REST_ID);
        Vote vote = service.get(CREATED_VOTE_ID);
        assertMatch(CREATED_VOTE, vote);
    }

    //User changed his vote by one date:
    @Test
    public void update() {
        service.save(LocalDateTime.of(VOTE_DATE, LocalTime.of(10, 0)), USER_1_ID, RUSS_REST_ID);
        Vote vote = service.getByUserAndDate(USER_1_ID, VOTE_1.getDate());
        assertMatch(vote, UPDATED_VOTE);
    }

    @Test(expected = VotingTimeIsOutException.class)
    public void updateTimeIsOut() {
        service.save(LocalDateTime.of(VOTE_DATE, LocalTime.of(15, 0)), USER_1_ID, RUSS_REST_ID);
        Vote vote = service.getByUserAndDate(USER_1_ID, VOTE_1.getDate());
        assertMatch(vote, UPDATED_VOTE);
    }

    @Test
    public void delete() {
        service.delete(VOTE_1_ID);
        List<Vote> all = service.getAll();
        assertMatch(all, ALL_VOTES_EXCEPT_FIRST);
    }

    @Test
    public void countVotes() {
        Long votes = service.countVotes(VOTE_DATE, EURO_REST_ID);
        assertThat(votes).isEqualTo(2L);
    }
}