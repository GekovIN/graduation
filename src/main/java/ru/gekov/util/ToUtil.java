package ru.gekov.util;

import ru.gekov.model.Role;
import ru.gekov.model.User;
import ru.gekov.to.UserTo;

public class ToUtil {

    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setRoles(userTo.getRoles());
        user.setEnabled(userTo.isEnabled());
        return user;
    }
}
