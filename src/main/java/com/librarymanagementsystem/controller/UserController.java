package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.responses.ApiResponse;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;
import com.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        try{
            RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,registerUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }

}
