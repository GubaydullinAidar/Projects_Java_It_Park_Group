package ru.itpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/changeUser")
    public ResponseEntity<User> saveChangeUser(@RequestBody UserForSignUp newuser,
                                               @RequestHeader("Auth-token") String token) {
        User user = userService.findByToken(token);
        user.setFirstName((newuser.getFirstName()));
        user.setLastName(newuser.getLastName());
        user.setEmail(newuser.getEmail());
        user.setPhone(newuser.getPhone());

        userService.saveUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

