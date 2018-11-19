package ru.gekov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gekov.model.Role;
import ru.gekov.model.User;

import java.util.List;

import static org.junit.Assert.*;
import static ru.gekov.UserTestData.*;

public class UserServiceImplTest extends ServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        assertMatch(all, ALL_USERS);
    }

    @Test
    public void get() {
        User user = service.get(USER_1_ID);
        assertMatch(user, USER_1);
    }

    @Test
    public void create() {
        User newUser = new User("New User", "newmail@gmail.com", "newPassword", Role.ROLE_USER);
        User saved = service.save(newUser);
        User user = service.get(saved.getId());
        assertMatch(user, saved);
    }

    @Test
    public void delete() {
        service.delete(USER_1_ID);
        List<User> all = service.getAll();
        assertMatch(all, ALL_USERS_EXCEPT_FIRST);
    }
}