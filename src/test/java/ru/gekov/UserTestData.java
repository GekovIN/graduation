package ru.gekov;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.gekov.model.Role;
import ru.gekov.model.User;
import ru.gekov.web.json.JsonUtil;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.TestUtil.readFromJsonMvcResult;
import static ru.gekov.VoteTestData.*;

public class UserTestData {

    public static final int USER_1_ID = 100000;
    public static final int USER_2_ID = USER_1_ID+1;
    public static final int USER_3_ID = USER_1_ID+2;

    public static final int ADMIN_ID = USER_1_ID+3;

    public static final User USER_1 = new User(USER_1_ID, "User1", "user1@yandex.ru", "password1", Role.ROLE_USER);
    public static final User USER_2 = new User(USER_2_ID, "User2", "user2@yandex.ru", "password2", Role.ROLE_USER);
    public static final User USER_3 = new User(USER_3_ID, "User3", "user3@yandex.ru", "password3", Role.ROLE_USER);

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final List<User> ALL_USERS = List.of(USER_1, USER_2, USER_3, ADMIN);
    public static final List<User> ALL_USERS_AFTER_DELETE = List.of(USER_2, USER_3, ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password", "votes");
    }

    public static void assertMatchWithVotes(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "password", "votes").isEqualTo(expected);
    }

    public static User getWithVotes() {
        User user = new User(USER_1);
        user.setVotes(Set.of(VOTE_1, VOTE_4));
        return user;
    }

    public static ResultMatcher getUserMatcher(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static ResultMatcher getUserWithVotesMatcher(User expected) {
        return result -> assertMatchWithVotes(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String password) {
        return JsonUtil.writeAdditionProps(user, "password", password);
    }

}
