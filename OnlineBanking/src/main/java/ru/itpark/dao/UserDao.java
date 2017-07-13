package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.models.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
