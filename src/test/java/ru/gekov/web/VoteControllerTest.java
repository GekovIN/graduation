package ru.gekov.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.gekov.model.Vote;
import ru.gekov.service.VoteService;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gekov.RestaurantTestData.EURO_REST;
import static ru.gekov.TestUtil.readFromJsonResultActions;
import static ru.gekov.TestUtil.userHttpBasic;
import static ru.gekov.UserTestData.ADMIN;
import static ru.gekov.UserTestData.USER_1;
import static ru.gekov.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {

    @Autowired
    VoteService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getVotesMatcher(ALL_VOTES))
                .andDo(print());
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get("/votes/100000")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getVoteMatcher(VOTE_1))
                .andDo(print());
    }

    @Test
    void testGetProfileVoteByDate() throws Exception {
        mockMvc.perform(get("/profile/vote?date=2018-10-31")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getVoteMatcher(VOTE_4))
                .andDo(print());
    }

    @Test
    void testGetVoteByUserIdAndDate() throws Exception {
        mockMvc.perform(get("/users/100000/vote?date=2018-10-29")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getVoteMatcher(VOTE_1))
                .andDo(print());
    }

    @Test
    void vote() throws Exception {
        Vote created = new Vote(LocalDate.now(), USER_1, EURO_REST);
        ResultActions result = mockMvc.perform(put("/profile/vote?restaurantId=100000")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isCreated());

        Vote returned = readFromJsonResultActions(result, Vote.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(service.getAll(), VOTE_1, VOTE_2, VOTE_3, VOTE_4, VOTE_5, VOTE_6, created);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/votes/100000")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(service.getAll(), ALL_VOTES_AFTER_DELETE);
    }
}