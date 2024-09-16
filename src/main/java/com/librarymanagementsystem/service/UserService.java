package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dtos.request.BorrowBookRequest;
import com.librarymanagementsystem.dtos.request.LoginUserRequest;
import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.request.UpdateUserRequest;
import com.librarymanagementsystem.dtos.responses.BorrowBookResponse;
import com.librarymanagementsystem.dtos.responses.LoginUserResponse;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;
import com.librarymanagementsystem.dtos.responses.UpdateUserResponse;

public interface UserService {
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest);
}
