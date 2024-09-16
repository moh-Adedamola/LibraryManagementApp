package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.repositories.UserRepository;
import com.librarymanagementsystem.dtos.request.LoginUserRequest;
import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.request.UpdateUserRequest;
import com.librarymanagementsystem.dtos.responses.LoginUserResponse;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;
import com.librarymanagementsystem.dtos.responses.UpdateUserResponse;
import com.librarymanagementsystem.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

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

    @Test
    public void testToLoginUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("yinka");
        registerUserRequest.setLastName("obanla");
        registerUserRequest.setEmail("obanla@gmail.com");
        registerUserRequest.setPassword("password");
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Registration Successful");

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("obanla@gmail.com");
        loginUserRequest.setPassword("password");
        LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
        assertNotNull(loginUserResponse);
        assertThat(loginUserResponse.getMessage()).isEqualTo("Login Successful");


    }

    @Test
    public void testToUpdateUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("sekinat");
        registerUserRequest.setLastName("solonge");
        registerUserRequest.setEmail("sekinat@gmail.com");
        registerUserRequest.setPassword("password");
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Registration Successful");

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setEmail("sekinat@gmail.com");
        loginUserRequest.setPassword("password");
        LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
        assertNotNull(loginUserResponse);
        assertThat(loginUserResponse.getMessage()).isEqualTo("Login Successful");

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setFirstName("sekinat");
        updateUserRequest.setLastName("bisiriyu");
        updateUserRequest.setPassword("095770");
        UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
        assertNotNull(updateUserResponse);
        assertThat(updateUserResponse.getMessage()).isEqualTo("Update details Successful");

    }

}