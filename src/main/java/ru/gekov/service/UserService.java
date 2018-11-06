package ru.gekov.service;

import ru.gekov.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User get(int id);
    User update(User restaurant);
    User create(User restaurant);
    boolean delete(int id);
}
