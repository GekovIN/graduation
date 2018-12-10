package ru.gekov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gekov.model.User;
import ru.gekov.service.UserService;
import ru.gekov.to.UserTo;
import ru.gekov.util.UserUtil;
import ru.gekov.util.ValidationUtil;

import javax.validation.Valid;
import java.util.List;

import static ru.gekov.util.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = AdminController.REST_URL)
public class AdminController {

    static final String REST_URL = "/admin/users";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService service;

    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Integer id) {
        log.info("get user with id=", authUserId());
        return service.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.info("delete user with id=", id);
        service.delete(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            return ValidationUtil.processBindingErrors(result);
        }
        if (userTo.isNew()) {
            service.create(UserUtil.createNewFromTo(userTo));
        } else {
            service.update(userTo, userTo.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
