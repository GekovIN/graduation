package ru.gekov.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.gekov.model.Dish;
import ru.gekov.service.DishService;
import ru.gekov.web.json.JsonUtil;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gekov.DishTestData.*;
import static ru.gekov.TestUtil.readFromJsonResultActions;
import static ru.gekov.TestUtil.userHttpBasic;
import static ru.gekov.UserTestData.ADMIN;
import static ru.gekov.UserTestData.USER_1;
import static ru.gekov.util.exception.ErrorType.*;

class DishesControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishesController.REST_URL + '/';

    @Autowired
    private DishService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getDishesMatcher(ALL_DISHES));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + EURO_DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getDishMatcher(EURO_DISH_1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + EURO_DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(service.getAll(), ALL_DISHES_AFTER_DELETE);
    }

    @Test
    void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions result = mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Dish returned = readFromJsonResultActions(result, Dish.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), ALL_DISHES_AFTER_CREATE);
    }

    @Test
    void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + EURO_DISH_1_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)));

        assertMatch(service.get(EURO_DISH_1_ID), updated);
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 9999)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 9999)
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
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER_1)))
                .andExpect(status().isInternalServerError())
                .andExpect(errorType(APP_ERROR))
                .andExpect(detailMessage("Access is denied"));
    }

    @Test
    void testCreateInvalid() throws Exception {
        Dish invalid = new Dish(null, null, new BigDecimal(1000));

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
        Dish invalid = new Dish(EURO_DISH_1_ID, null, new BigDecimal(1000));

        mockMvc.perform(put(REST_URL + EURO_DISH_1_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void testUpdateHtmlUnsafe() throws Exception {
        Dish invalid = new Dish(EURO_DISH_1_ID, "<script>alert(123)</script>", new BigDecimal(1000));

        mockMvc.perform(put(REST_URL + EURO_DISH_1_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }
}