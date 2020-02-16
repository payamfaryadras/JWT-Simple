package com.payam.learn.usermanagement.services;

import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.exception.EntityType;
import com.payam.learn.usermanagement.exception.ExceptionType;
import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  CustomException customException;

    public User createUser(User user) {
        if ((user.getPassword() == null || user.getPassword().isBlank()) ||
                (user.getEmail() == null || user.getEmail().isBlank())) {
            throw new IllegalStateException("email and pass cannot be blank.");
        } else {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw  customException.throwExceptionWithTemplate(EntityType.USER
                        , ExceptionType.DUPLICATE_ENTITY, user.getEmail());
            }
        }
        return userRepository.save(user);
    }


}
