package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.model.User;
import ru.gekov.repository.UserRepository;
import ru.gekov.to.UserTo;
import ru.gekov.util.NotFoundException;
import ru.gekov.util.UserUtil;
import ru.gekov.util.ValidationUtil;

import java.util.List;
import java.util.Optional;

import static ru.gekov.util.ValidationUtil.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public void update(UserTo userTo, int id) {
        Optional<User> optional = repository.findById(userTo.getId());
        User user = checkNotFoundOptionalWithId(optional, userTo.getId());
        ValidationUtil.assureIdConsistent(user, id);
        repository.save(UserUtil.updateFromTo(user, userTo));
    }


}
