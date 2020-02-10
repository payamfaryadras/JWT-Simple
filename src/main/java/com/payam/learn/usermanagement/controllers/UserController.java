package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, HttpServletRequest request, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
        }
    }

}
