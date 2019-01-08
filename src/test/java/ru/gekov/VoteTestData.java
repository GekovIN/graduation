package ru.gekov;

import org.springframework.test.web.servlet.ResultMatcher;
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
    public static final int VOTE_4_ID = VOTE_1_ID+3;
    public static final int VOTE_5_ID = VOTE_1_ID+4;
    public static final int VOTE_6_ID = VOTE_1_ID+5;
    public static final int CREATED_VOTE_ID = VOTE_1_ID+6;
    public static final LocalDate VOTE_DATE_2018_10_29 = LocalDate.of(2018, 10, 29);
    public static final LocalDate VOTE_DATE_2018_10_31 = LocalDate.of(2018, 10, 31);

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, VOTE_DATE_2018_10_29, USER_1, EURO_REST);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, VOTE_DATE_2018_10_29, USER_2, EURO_REST);
    public static final Vote VOTE_3 = new Vote(VOTE_3_ID, VOTE_DATE_2018_10_29, USER_3, THAI_REST);

    public static final Vote VOTE_4 = new Vote(VOTE_4_ID, VOTE_DATE_2018_10_31, USER_1, EURO_REST);
    public static final Vote VOTE_5 = new Vote(VOTE_5_ID, VOTE_DATE_2018_10_31, USER_2, EURO_REST);
    public static final Vote VOTE_6 = new Vote(VOTE_6_ID, VOTE_DATE_2018_10_31, ADMIN, EURO_REST);

    public static final Vote UPDATED_VOTE = new Vote(VOTE_1_ID, VOTE_1.getDate(), VOTE_1.getUser(), RUSS_REST);

    public static final List<Vote> ALL_VOTES = List.of(VOTE_1, VOTE_2, VOTE_3, VOTE_4, VOTE_5, VOTE_6);
    public static final List<Vote> ALL_VOTES_EXCEPT_FIRST = List.of(VOTE_2, VOTE_3);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static ResultMatcher getVotesMatcher(Iterable<Vote> expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, Vote.class), expected);
    }

    public static ResultMatcher getVoteMatcher(Vote expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, Vote.class), expected);
    }

    public static Vote getNewVote() {
        return new Vote(CREATED_VOTE_ID, LocalDate.now(), USER_1, EURO_REST);
    }
}
