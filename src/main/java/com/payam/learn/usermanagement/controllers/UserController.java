package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public User createUser(@Valid @RequestBody User user, HttpServletRequest request){
        return userService.createUser(user);
    }

}
