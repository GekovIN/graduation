package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.gekov.AuthorizedUser;
import ru.gekov.model.User;
import ru.gekov.repository.UserRepository;
import ru.gekov.to.UserTo;
import ru.gekov.util.ToUtil;
import ru.gekov.util.exception.InvalidOldPasswordException;

import java.util.List;

import static ru.gekov.util.EntitiesUtil.*;
import static ru.gekov.util.ValidationUtil.*;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public User getWithVotes(int id) {
        return checkNotFoundWithId(repository.findByIdWithVotes(id), id);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareUserToSave(user, passwordEncoder));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = ToUtil.updateFromTo(get(userTo.getId()), userTo);
        repository.save(user);
    }

//  Custom update method for user updating his profile (do not change roles and enabled):
    @Override
    public void updateProfile(UserTo userTo, int id) {
        assureToIdConsistent(userTo, id);
        User user = get(userTo.getId());
        user.setEmail(userTo.getEmail());
        user.setName(userTo.getName());
        repository.save(user);
    }

    @Override
    public void changeUserPassword(int id, String oldPassword, String newPassword) {
        User user = get(id);
        if (!checkIfOldPasswordValid(user.getPassword(), oldPassword)) {
            throw new InvalidOldPasswordException("Wrong old password.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    private boolean checkIfOldPasswordValid(String persistPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, persistPassword);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " not found");
        }
        return new AuthorizedUser(user);
    }
}
