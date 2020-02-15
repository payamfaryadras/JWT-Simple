package com.payam.learn.usermanagement.service;


import com.payam.learn.usermanagement.exception.CustomException;
import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.repositories.UserRepository;
import com.payam.learn.usermanagement.services.UserService;
import org.h2.security.auth.ConfigProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Properties;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;



    @Test(expected = CustomException.DuplicateEntityException.class)
    public void createUser_should_throws_duplicateException_when_theUser_already_exist() {
        //given
        User user = new User();
        user.setEmail("Test@Test.com");
        user.setPassword("pass123");

        //when
        when(userRepository.findByEmail("Test@Test.com")).thenReturn(Optional.of(user));

        //then
        userService.createUser(user);
    }


    @Test(expected = IllegalStateException.class)
    public void createUser_should_throws_IllegalEntityStateException_when_email_is_null(){
        User user = new User();
        user.setPassword("pass123");
        userService.createUser(user);
    }
    @Test(expected = IllegalStateException.class)
    public void createUser_should_throws_IllegalEntityStateException_when_password_is_null(){
        User user = new User();
        user.setEmail("Test@Test.com");
        userService.createUser(user);
    }

    @Test(expected = IllegalStateException.class)
    public void createUser_should_throws_IllegalEntityStateException_when_email_is_blank(){
        User user = new User();
        user.setPassword("pass123");
        user.setEmail(" ");
        userService.createUser(user);
    }
    @Test(expected = IllegalStateException.class)
    public void createUser_should_throws_IllegalEntityStateException_when_password_is_blank(){
        User user = new User();
        user.setEmail("Test@Test.com");
        user.setPassword("  ");
        userService.createUser(user);
    }
}
