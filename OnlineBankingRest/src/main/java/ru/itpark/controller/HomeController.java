package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.dto.UserForSignUp;
import ru.itpark.models.User;
import ru.itpark.service.UserService;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping("/tokenValid")
    public ResponseEntity<Boolean> tokenValid(@RequestHeader("token") String token) {
        return new ResponseEntity<>(userService.findByToken(token) != null, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserForSignUp user) {
        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
            String errorMessage = "";
            if (userService.checkEmailExists(user.getEmail())) {
                errorMessage += "Пользователь с таким Email зарегистрирован.\n";
            }
            if (userService.checkUsernameExists(user.getUsername())) {
                errorMessage += "Логин занят, выберите другой Логин";
            }

            return new ResponseEntity<>(errorMessage, HttpStatus.OK);
        } else {
            userService.createUser(user);
            String signupOk = "Регистрация прошла успешно";
            return new ResponseEntity<>(signupOk, HttpStatus.OK);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<User> login(@RequestHeader("username") String username,
                                      @RequestHeader("password") String password) {
        String token = userService.login(username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Token", token);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> getUser(@RequestHeader("Auth-Token") String token) {
        return new ResponseEntity<>(userService.findByToken(token), HttpStatus.OK);
    }


}