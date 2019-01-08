package ru.gekov.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.service.VoteService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
        mockMvc.perform(put("/profile/100000/vote")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk());

        assertMatch(service.get(CREATED_VOTE_ID), getNewVote());
    }
}