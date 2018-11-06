package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.User;
import ru.gekov.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User update(User restaurant) {
        return null;
    }

    @Override
    public User create(User restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
