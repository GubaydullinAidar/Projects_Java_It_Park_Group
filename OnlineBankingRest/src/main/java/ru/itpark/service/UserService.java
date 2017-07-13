package ru.itpark.service;

import ru.itpark.dto.UserForSignUp;
import ru.itpark.models.User;

public interface UserService {

    User findByUsername(String username);

    User findByUserId(Long userId);

    User findByEmail(String email);

    User findByToken (String token);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    User createUser(UserForSignUp user);

    User saveUser(User user);

    String login(String login, String password);
}
