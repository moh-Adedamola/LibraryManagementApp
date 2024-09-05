package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;
import com.librarymanagementsystem.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testToRegisterUser() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("mohammed");
        registerUserRequest.setLastName("adegbite");
        registerUserRequest.setEmail("moh@gmail.com");
        registerUserRequest.setPassword("password");
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Registration Successful");
//        assertThrows(UserAlreadyExistException.class, ()-> userService.registerUser(registerUserRequest));


    }

}