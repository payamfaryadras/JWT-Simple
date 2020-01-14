package com.payam.learn.usermanagement.controllers;

import com.payam.learn.usermanagement.models.User;
import com.payam.learn.usermanagement.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUser_should_createUser_when_validRequest() throws Exception {
        User user = new User();
        user.setEmail("Test@Test.com");
        user.setPassword("pass123");
        given(userService.createUser(any(User.class))).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ password:'pass123', email: payam.ff@gmail.com }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("Test@Test.com"))
                .andExpect(jsonPath("$.password").value("pass123"));
    }

    @Test
    public void createUser_should_return_400_when_request_isNotValid() throws Exception {
        String userInJson = "{\"email\":\"\",\"password\":\"\"}";
        User user = new User();
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                .content(userInJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userService,times(0)).createUser(user);


    }

}
