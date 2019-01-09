package ru.gekov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.gekov.util.exception.VotingTimeIsOutException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static ru.gekov.RestaurantTestData.*;
import static ru.gekov.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteServiceImplTest {

    @Autowired
    private VoteService service;

    @Test
    void voteAfterTimeIsOver() {
        VotingTimeIsOutException exception = assertThrows(VotingTimeIsOutException.class,
                () -> service.save(LocalDateTime.of(2018, 10, 29, 12, 0), USER_1_ID, THAI_REST_ID));
        String message = exception.getMessage();
        assertTrue(message.contains("Vote time"));
    }
}