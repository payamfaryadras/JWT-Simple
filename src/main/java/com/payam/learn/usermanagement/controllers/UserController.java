package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public User createUser(User user){
        return userService.createUser(user);
    }

}
