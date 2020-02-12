package com.payam.learn.usermanagement.services;

import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public User createUser(User user) {
        if ((user.getPassword() != null || !user.getPassword().isBlank()) &&
               (user.getEmail() != null || !user.getEmail().isBlank())) {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//TODO
                throw new CustomException.DuplicateEntityException("the user already exist.");
            }
        } else {
            throw new CustomException.IllegalEntityStateException("email and pass cannot be blank.");
        }

        return userRepository.save(user);
    }


}
