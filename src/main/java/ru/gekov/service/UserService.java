package ru.gekov.service;

import ru.gekov.model.User;
import ru.gekov.to.UserTo;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User get(int id);
    void delete(int id);

    User getWithVotes(int id);

    User create(User user);

    void update(User user);

    void update(UserTo userTo, int id);

    User getByEmail(String email);
}
