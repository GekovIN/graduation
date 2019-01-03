package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.gekov.AuthorizedUser;
import ru.gekov.model.Vote;
import ru.gekov.service.VoteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.*;
import static org.springframework.http.MediaType.*;

@RestController
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/votes", produces = APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public List<Vote> getAll() {
        log.info("get all votes");
        return service.getAll();
    }

    @GetMapping(value = "/profile/vote", params = "date", produces = APPLICATION_JSON_VALUE)
    public Vote getProfileVoteByDate(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                     @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get profile vote by date {}", date);
        return service.getByUserAndDate(authUser.getId(), date);
    }

    @GetMapping(value = "/users/{userId}/vote", params = "date", produces = APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Vote getVoteByUserIdAndDate(@PathVariable Integer userId, @RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
        log.info("get vote by user {} and date {}", userId, date);
        return service.getByUserAndDate(userId, date);
    }

    //  Vote for restaurant by authorized user
//    @PutMapping(value = "/profile/vote")
//    public void vote(@RequestParam("restaurantId") Integer restaurantId) {
//        int userId = SecurityUtil.authUserId();
//        log.info("user with id={} vote for restaurant with id={}", userId, restaurantId);
//        service.save(LocalDateTime.now(), userId, restaurantId);
//    }

//  Test
    @PutMapping(value = "/restaurant/{id}/vote")
    public void vote(@PathVariable("id") Integer restaurantId,
                     @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("user with id={} vote for restaurant with id={}", userId, restaurantId);
        service.save(LocalDateTime.of(2018, 12, 11, 10, 0), 100000, restaurantId);
    }

}
