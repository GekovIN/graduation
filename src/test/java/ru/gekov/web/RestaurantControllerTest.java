package ru.gekov.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.gekov.model.Restaurant;
import ru.gekov.service.RestaurantService;
import ru.gekov.web.json.JsonUtil;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gekov.RestaurantTestData.*;
import static ru.gekov.TestUtil.readFromJsonResultActions;
import static ru.gekov.TestUtil.userHttpBasic;
import static ru.gekov.UserTestData.ADMIN;
import static ru.gekov.UserTestData.USER_1;
import static ru.gekov.util.exception.ErrorType.APP_ERROR;
import static ru.gekov.util.exception.ErrorType.VALIDATION_ERROR;

class RestaurantControllerTest extends AbstractControllerTest {

    private final String REST_URL = RestaurantController.REST_URL;
    private final String REST_URL_SLASH = RestaurantController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsMatcher(ALL_RESTAURANTS));
    }

    @Test
    void testGetAllWithMenuDishes() throws Exception {
        mockMvc.perform(get(REST_URL + "/menus")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsWithMenusMatcher(getAllRestaurantsWithMenus()))
                .andDo(print());
    }

    @Test
    void testGetAllWithVotes() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + "votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsWithVotesMatcher(getAllRestaurantsWithVotes()))
                .andDo(print());
    }

    @Test
    void getAllWithMenuDishesAndVotes() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + "menus-and-votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsWithVotesMatcher(getAllRestaurantsWithMenusAndVotes()))
                .andDo(print());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(EURO_REST))
                .andDo(print());
    }

    @Test
    void testGetByIdWithAllMenus() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/menus")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcherWithMenus(getEuroRestWithMenus()))
                .andDo(print());
    }

    @Test
    void testGetByIdWithAllVotes() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcherWithMenus(getEuroRestWithVotes()))
                .andDo(print());
    }

    @Test
    void testGetByIdWithMenusAndVotes() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/menus-and-votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcherWithMenus(getEuroRestWithMenusAndVotes()))
                .andDo(print());
    }

    @Test
    void testGetHaveMenuByDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + "haveMenu?date=2018-10-31")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsMatcher(List.of(EURO_REST, RUSS_REST)))
                .andDo(print());
    }

    @Test
    void testGetAllWithMenuByDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + "menus?date=2018-10-31")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsMatcher(List.of(getRussRestWithMenus(), getEuroRestWithMenus())))
                .andDo(print());
    }

    @Test
    void testGetAllWithVotesByDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + "votes?date=2018-10-31")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsMatcher(List.of(getEuroRestWithVotes_2018_10_31(), getRussRestWithVotes())))
                .andDo(print());
    }

    @Test
    void testGetWithMenuByIdAndDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/menus?date=2018-10-29")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(getEuroRestWithMenus_2018_10_29()))
                .andDo(print());
    }

    @Test
    void testGetWithVotesByIdAndDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/votes?date=2018-10-29")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(getEuroRestWithVotes_2018_10_31()))
                .andDo(print());
    }

    @Test
    void testGetWithMenuAndVotesByIdAndDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/menus-and-votes?date=2018-10-29")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(getEuroRestWithMenusAndVotes_2018_10_29()))
                .andDo(print());
    }

    @Test
    void testGetWithVotesCountByIdAndDate() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_REST_ID + "/votes-count?date=2018-10-31")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantToMatcher(EURO_REST_TO))
                .andDo(print());
    }

    @Test
    void testGetWithVotesCountByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "/votes-count?date=2018-10-31")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getRestaurantsToMatcher(List.of(EURO_REST_TO, RUSS_REST_TO)))
                .andDo(print());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL_SLASH + EURO_REST_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(service.getAll(), ALL_RESTAURANTS_AFTER_DELETE);
    }

    @Test
    void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions result = mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = readFromJsonResultActions(result, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), ALL_RESTAURANTS_AFTER_CREATE);
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        mockMvc.perform(put(REST_URL_SLASH + EURO_REST_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)));

        assertMatch(service.get(EURO_REST_ID), updated);
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL_SLASH + 1)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testWrongRole() throws Exception {
        mockMvc.perform(delete(REST_URL_SLASH + EURO_REST_ID)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isInternalServerError())
                .andExpect(errorType(APP_ERROR))
                .andExpect(detailMessage("Access is denied"));
    }

    @Test
    void testCreateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, null, "new address");

        mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void testUpdateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(EURO_REST_ID, null, "Updated address");

        mockMvc.perform(put(REST_URL_SLASH + EURO_REST_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void testUpdateHtmlUnsafe() throws Exception {
        Restaurant invalid = new Restaurant(EURO_REST_ID, "Updated name", "<script>alert(123)</script>");

        mockMvc.perform(put(REST_URL_SLASH + EURO_REST_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }
}