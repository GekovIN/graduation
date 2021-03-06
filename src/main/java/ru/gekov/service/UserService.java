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

    void update(UserTo userTo);

    User getByEmail(String email);

    void updateProfile(UserTo userTo, int id);

    void changeUserPassword(int id, String oldPassword, String newPassword);
}
