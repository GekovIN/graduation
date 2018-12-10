package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.User;
import ru.gekov.service.UserService;
import ru.gekov.to.UserTo;

import static ru.gekov.util.SecurityUtil.authUserId;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        log.info("get user with id=", authUserId());
        return service.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        log.info("delete user with id=", authUserId());
        service.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
