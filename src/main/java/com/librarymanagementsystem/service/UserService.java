package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
}
