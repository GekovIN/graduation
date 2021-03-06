package ru.gekov.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gekov.model.User;
import ru.gekov.service.UserService;
import ru.gekov.to.UserTo;
import ru.gekov.web.json.View;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.*;
import static ru.gekov.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminController.REST_URL)
@Secured("ROLE_ADMIN")
public class AdminController {

    static final String REST_URL = "/admin/users";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonProfile.class)
    public User get(@PathVariable Integer id) {
        log.info("get user with id={}", id);
        return service.get(id);
    }

    @GetMapping(value = "/by", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonProfile.class)
    public User getByMail(@RequestParam("email") String email) {
        log.info("get user with email {}", email);
        return service.getByEmail(email);
    }

    @GetMapping(value = "/{id}/votes", produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonUserWithVotes.class)
    public User getByIdWithVotes(@PathVariable Integer id) {
        log.info("get user {} with all votes", id);
        return service.getWithVotes(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonProfile.class)
    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @JsonView(View.JsonProfile.class)
    public ResponseEntity<User> createWithNewUri(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = service.create(user);
        URI newResourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(newResourceUri).body(created);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @PathVariable Integer id) {
        log.info("update {} with id={}", userTo, id);
        assureToIdConsistent(userTo, id);
        service.update(userTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.info("delete user with id={}", id);
        service.delete(id);
    }

}
