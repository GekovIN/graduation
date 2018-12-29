package ru.gekov.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gekov.model.User;
import ru.gekov.service.UserService;
import ru.gekov.to.UserTo;
import ru.gekov.util.ToUtil;
import ru.gekov.util.ValidationUtil;
import ru.gekov.web.json.View;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.*;
import static ru.gekov.util.SecurityUtil.authUserId;
import static ru.gekov.util.ToUtil.*;
import static ru.gekov.util.ValidationUtil.*;

@RestController
@RequestMapping(ProfileController.REST_URL)
public class ProfileController {
    static final String REST_URL = "/profile";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public ProfileController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonProfile.class)
    public User get() {
        log.info("get user with id=", authUserId());
        return service.get(authUserId());
    }

    @GetMapping(value = "/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUserWithVotes.class)
    public User getWithVotes() {
        log.info("get user {} with all votes", authUserId());
        return service.getWithVotes(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        log.info("delete user with id=", authUserId());
        service.delete(authUserId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        User created = service.create(createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo) {
        log.info("update user with id=", authUserId());
        service.update(userTo, authUserId());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}
