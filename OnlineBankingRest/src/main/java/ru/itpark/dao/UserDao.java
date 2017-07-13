package ru.itpark.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itpark.models.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUserId(Long userId);

    User findByEmail(String email);

    List<User> findAll();

    User findByToken(String token);

    @Query("select count(user) > 0 from User user where user.token = ?1")
    boolean isExistToken(String token);

    @Modifying
    @Query("update User user set user.token = ?2 where user.id = ?1")
    void updateToken(Long id, String token);
}
