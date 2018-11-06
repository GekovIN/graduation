package ru.gekov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gekov.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
