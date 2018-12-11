package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.Vote;
import ru.gekov.service.VoteService;
import ru.gekov.util.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class VoteController {

    static final String REST_URL = "/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/admin/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        log.info("get all votes");
        return service.getAll();
    }

    //Get current date vote for authorized user
    @GetMapping(value = "/profile/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getCurrentDateProfileVote() {
        log.info("get current date profile vote");
        return service.getByUserAndDate(SecurityUtil.authUserId(), LocalDate.now());
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
    public void vote(@PathVariable("id") Integer restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("user with id={} vote for restaurant with id={}", userId, restaurantId);
        service.save(LocalDateTime.of(2018, 12, 11, 10, 0), 100000, restaurantId);
    }

//    @GetMapping(params = "date", value = "/restaurant/{id}/votes/number/by")
//    public VoteNumberTo getNumberByRestaurantAndDate(@PathVariable("id") Integer restaurantId,
//                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//                                                     @RequestParam("date") LocalDate date) {
//        log.info("get number of votes by restaurant with id={} and date={}", restaurantId, date);
//        return service.getNumberByRestaurantAndDate(restaurantId, date);
//    }

//    @GetMapping(value = "/restaurant/{id}/votes")
//    public Restaurant getByRestaurant(@PathVariable Integer restaurantId) {
//
//    }

}
