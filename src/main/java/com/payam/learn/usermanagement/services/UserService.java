package com.payam.learn.usermanagement.services;

import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.save(user);
    }


}
