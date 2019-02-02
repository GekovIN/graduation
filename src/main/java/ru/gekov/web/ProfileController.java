package ru.gekov.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gekov.AuthorizedUser;
import ru.gekov.model.User;
import ru.gekov.service.UserService;
import ru.gekov.to.UserTo;
import ru.gekov.web.json.View;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.*;
import static ru.gekov.util.ToUtil.*;
import static ru.gekov.util.ValidationUtil.*;

@RestController
@RequestMapping(ProfileController.REST_URL)
@CrossOrigin("*")
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
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        int id = authUser.getId();
        log.info("get user with id={}", id);
        return service.get(id);
    }

    @GetMapping(value = "/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUserWithVotes.class)
    public User getWithVotes(@AuthenticationPrincipal AuthorizedUser authUser) {
        int id = authUser.getId();
        log.info("get user with id={} with all votes", id);
        return service.getWithVotes(id);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        int id = authUser.getId();
        log.info("delete user with id={}", id);
        service.delete(id);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView(View.JsonProfile.class)
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
    public void update(@RequestBody @Valid UserTo userTo,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        int id = authUser.getId();
        log.info("update user with id={}", id);
        service.update(userTo, id);
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}
