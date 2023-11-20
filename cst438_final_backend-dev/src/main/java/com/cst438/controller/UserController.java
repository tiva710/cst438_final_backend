package com.cst438.controller;

import com.cst438.domain.User1;
import com.cst438.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User1 registerUser(@RequestBody User1 user) {
        return userService.registerUser(user);
    }
}
