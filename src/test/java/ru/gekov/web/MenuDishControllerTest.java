package ru.gekov.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.service.MenuDishService;
import ru.gekov.to.MenuDishTo;
import ru.gekov.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gekov.DishTestData.*;
import static ru.gekov.MenuDishTestData.*;
import static ru.gekov.RestaurantTestData.*;
import static ru.gekov.TestUtil.userHttpBasic;
import static ru.gekov.UserTestData.ADMIN;
import static ru.gekov.UserTestData.USER_1;
import static ru.gekov.util.exception.ErrorType.APP_ERROR;
import static ru.gekov.util.exception.ErrorType.VALIDATION_ERROR;

class MenuDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuDishController.REST_URL;
    private static final String REST_URL_SLASH = MenuDishController.REST_URL + "/";

    @Autowired
    MenuDishService service;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMenuDishesMatcher(ALL_MENU));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + EURO_MENU_DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMenuDishMatcher(EURO_MENU_DISH_1));
    }

    @Test
    void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=2018-10-29")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMenuDishesMatcher(EURO_MENU_2018_10_29));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL_SLASH + EURO_MENU_DISH_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(service.getAll(), ALL_MENU_AFTER_DELETE);
    }

    @Test
    void testCreate() throws Exception {
        MenuDishTo createdTo = getCreatedTo();

        mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo))
                .with(userHttpBasic(ADMIN)));

        assertMatch(service.getAll(), ALL_MENU_AFTER_CREATE);
    }

    @Test
    void testUpdate() throws Exception {
        MenuDishTo updated = getUpdatedTo();
        mockMvc.perform(put(REST_URL_SLASH + EURO_MENU_DISH_1_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)));

        assertMatch(service.getById(EURO_MENU_DISH_1_ID), UPDATED);
    }

    @Test
    void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + 9999)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testDeleteNotFound() throws Exception {
        mockMvc.perform(get(REST_URL_SLASH + 9999)
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
        MenuDishTo invalid = new MenuDishTo(null, null, EURO_REST_ID, EURO_DISH_1_ID);

        mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void testCreateNotFoundForeignKey() throws Exception {
        MenuDishTo invalid = new MenuDishTo(null, LocalDate.of(2019, 1, 1), EURO_REST_ID, 1);

        mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isInternalServerError())
                .andExpect(errorType(APP_ERROR))
                .andExpect(detailMessage("Unable to find ru.gekov.model.Dish with id 1"))
                .andDo(print());
    }

    @Test
    void testUpdateInvalid() throws Exception {
        MenuDishTo invalid = new MenuDishTo(EURO_MENU_DISH_1_ID, null, EURO_REST_ID, EURO_DISH_1_ID);

        mockMvc.perform(put(REST_URL_SLASH + EURO_MENU_DISH_1_ID)
                .contentType(APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

}