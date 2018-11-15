package ru.gekov.service;

import ru.gekov.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User get(int id);
    User save(User restaurant);
    boolean delete(int id);
}
