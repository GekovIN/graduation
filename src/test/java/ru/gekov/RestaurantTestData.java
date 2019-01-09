package ru.gekov;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.gekov.model.Restaurant;
import ru.gekov.to.RestaurantVoteCountTo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gekov.MenuDishTestData.*;
import static ru.gekov.VoteTestData.*;

public class RestaurantTestData {

    public static final Integer EURO_REST_ID = 100000;
    public static final Integer THAI_REST_ID = EURO_REST_ID + 1;
    public static final Integer RUSS_REST_ID = EURO_REST_ID + 2;

    public static final Restaurant EURO_REST = new Restaurant(EURO_REST_ID, "Европейский ресторан", "Ленинский пр-т, д. 1");

    public static final Restaurant THAI_REST = new Restaurant(THAI_REST_ID, "Тайский ресторан", "Тверская ул., д. 10");

    public static final Restaurant RUSS_REST = new Restaurant(RUSS_REST_ID, "Русский ресторан", "Площадь им. Ленина, д. 18");

    public static final RestaurantVoteCountTo EURO_REST_TO = new RestaurantVoteCountTo(LocalDate.of(2018, 10, 31), EURO_REST, 2L);

    public static final RestaurantVoteCountTo RUSS_REST_TO = new RestaurantVoteCountTo(LocalDate.of(2018, 10, 31), RUSS_REST, 1L);

    public static final Restaurant CREATED = new Restaurant(EURO_REST_ID + 3, "New restaurant", "New address");

    public static final List<Restaurant> ALL_RESTAURANTS = List.of(EURO_REST, THAI_REST, RUSS_REST);

    public static final List<Restaurant> ALL_RESTAURANTS_AFTER_CREATE = List.of(EURO_REST, THAI_REST, RUSS_REST, CREATED);

    public static final List<Restaurant> ALL_RESTAURANTS_AFTER_DELETE = List.of(THAI_REST, RUSS_REST);

    public static Restaurant getCreated() {
        return new Restaurant("New restaurant", "New address");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(EURO_REST_ID, "Updated restaurant", "Updated address");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menuDishes", "votes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menuDishes", "votes").isEqualTo(expected);
    }

    public static void assertMatchWithMenus(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes");
    }

    public static void assertMatchWithMenus(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("votes").isEqualTo(expected);
    }

    public static void assertMatchWithVotes(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menuDishes");
    }

    public static void assertMatchWithVotes(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menuDishes").isEqualTo(expected);
    }

    public static void assertMatchTo(RestaurantVoteCountTo actual, RestaurantVoteCountTo expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatchTo(Iterable<RestaurantVoteCountTo> actual, Iterable<RestaurantVoteCountTo> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static ResultMatcher getRestaurantsMatcher(Iterable<Restaurant> expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantsWithMenusMatcher(Iterable<Restaurant> expected) {
        return result -> assertMatchWithMenus(TestUtil.readListFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantsWithVotesMatcher(Iterable<Restaurant> expected) {
        return result -> assertMatchWithVotes(TestUtil.readListFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantMatcher(Restaurant expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantMatcherWithMenus(Restaurant expected) {
        return result -> assertMatchWithMenus(TestUtil.readFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantMatcherWithVotes(Restaurant expected) {
        return result -> assertMatchWithVotes(TestUtil.readFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getRestaurantToMatcher(RestaurantVoteCountTo expected) {
        return result -> assertMatchTo(TestUtil.readFromJsonMvcResult(result, RestaurantVoteCountTo.class), expected);
    }

    public static ResultMatcher getRestaurantsToMatcher(Iterable<RestaurantVoteCountTo> expected) {
        return result -> assertMatchTo(TestUtil.readListFromJsonMvcResult(result, RestaurantVoteCountTo.class), expected);
    }

    public static Restaurant getEuroRestWithMenus() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setMenuDishes(Set.of(EURO_MENU_DISH_1,
                EURO_MENU_DISH_2, EURO_MENU_DISH_3, EURO_MENU_DISH_4, EURO_MENU_DISH_5, EURO_MENU_DISH_6));
        return euro;
    }

    public static Restaurant getThaiRestWithMenus() {
        Restaurant thai = new Restaurant(THAI_REST);
        thai.setMenuDishes(Set.of(THAI_MENU_DISH_1, THAI_MENU_DISH_2, THAI_MENU_DISH_3));
        return thai;
    }

    public static Restaurant getRussRestWithMenus() {
        Restaurant russ = new Restaurant(RUSS_REST);
        russ.setMenuDishes(Set.of(RUSS_MENU_DISH_1, RUSS_MENU_DISH_2, RUSS_MENU_DISH_3));
        return russ;
    }

    public static List<Restaurant> getAllRestaurantsWithMenus() {
        return List.of(getEuroRestWithMenus(), getThaiRestWithMenus(), getRussRestWithMenus());
    }

    public static Restaurant getEuroRestWithVotes() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setVotes(Set.of(VOTE_1, VOTE_2, VOTE_3, VOTE_4, VOTE_5));
        return euro;
    }

    public static Restaurant getThaiRestWithVotes() {
        Restaurant thai = new Restaurant(THAI_REST);
        thai.setVotes(Collections.emptySet());
        return thai;
    }

    public static Restaurant getRussRestWithVotes() {
        Restaurant russ = new Restaurant(RUSS_REST);
        russ.setVotes(Set.of(VOTE_6));
        return russ;
    }

    public static List<Restaurant> getAllRestaurantsWithVotes() {
        return List.of(getEuroRestWithVotes(), getThaiRestWithVotes(), getRussRestWithVotes());
    }

    public static Restaurant getEuroRestWithMenusAndVotes() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setMenuDishes(Set.of(EURO_MENU_DISH_1,
                EURO_MENU_DISH_2, EURO_MENU_DISH_3, EURO_MENU_DISH_4, EURO_MENU_DISH_5, EURO_MENU_DISH_6));
        euro.setVotes(Set.of(VOTE_1, VOTE_2, VOTE_3, VOTE_4, VOTE_5));
        return euro;
    }

    public static Restaurant getThaiRestWithMenusAndVotes() {
        Restaurant thai = new Restaurant(THAI_REST);
        thai.setMenuDishes(Set.of(THAI_MENU_DISH_1, THAI_MENU_DISH_2, THAI_MENU_DISH_3));
        thai.setVotes(Collections.emptySet());
        return thai;
    }

    public static Restaurant getRussRestWithMenusAndVotes() {
        Restaurant russ = new Restaurant(RUSS_REST);
        russ.setMenuDishes(Set.of(RUSS_MENU_DISH_1, RUSS_MENU_DISH_2, RUSS_MENU_DISH_3));
        russ.setVotes(Set.of(VOTE_6));
        return russ;
    }

    public static List<Restaurant> getAllRestaurantsWithMenusAndVotes() {
        return List.of(getEuroRestWithMenusAndVotes(), getThaiRestWithMenusAndVotes(), getRussRestWithMenusAndVotes());
    }

    public static Restaurant getEuroRestWithMenus_2018_10_29() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setMenuDishes(Set.of(EURO_MENU_DISH_1, EURO_MENU_DISH_2, EURO_MENU_DISH_3));
        return euro;
    }

    public static Restaurant getEuroRestWithVotes_2018_10_31() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setVotes(Set.of(VOTE_4, VOTE_5));
        return euro;
    }

    public static Restaurant getEuroRestWithMenusAndVotes_2018_10_29() {
        Restaurant euro = new Restaurant(EURO_REST);
        euro.setMenuDishes(Set.of(EURO_MENU_DISH_1, EURO_MENU_DISH_2, EURO_MENU_DISH_3));
        euro.setVotes(Set.of(VOTE_1, VOTE_2));
        return euro;
    }

}
