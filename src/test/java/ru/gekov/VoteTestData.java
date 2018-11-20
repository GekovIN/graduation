package ru.gekov;

import ru.gekov.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.RestaurantTestData.*;
import static ru.gekov.UserTestData.*;

public class VoteTestData {

    public static final int VOTE_1_ID = 100000;
    public static final int VOTE_2_ID = VOTE_1_ID+1;
    public static final int VOTE_3_ID = VOTE_1_ID+2;
    public static final int CREATED_VOTE_ID = VOTE_1_ID+3;
    public static final LocalDate VOTE_DATE = LocalDate.of(2018, 10, 29);

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, VOTE_DATE, USER_1, EURO_REST);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, VOTE_DATE, USER_2, EURO_REST);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, VOTE_DATE, USER_3, THAI_REST);

    public static final Vote CREATED_VOTE = new Vote(CREATED_VOTE_ID, LocalDate.of(2018, 1, 1), USER_3, RUSS_REST);
    public static final Vote UPDATED_VOTE = new Vote(VOTE_1_ID, VOTE_1.getDate(), VOTE_1.getUser(), RUSS_REST);
    public static final List<Vote> ALL_VOTES = List.of(VOTE_1, VOTE_2, VOTE_3);
    public static final List<Vote> ALL_VOTES_EXCEPT_FIRST = List.of(VOTE_2, VOTE_3);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
