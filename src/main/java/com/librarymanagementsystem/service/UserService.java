package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;

import java.util.List;

public interface UserService {
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest);

    List<Book> findAllBooks();

    Book findBookByTitle(String title);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByGenre(String genre);

    LogoutUserResponse logoutUser(LogoutUserRequest logoutUserRequest);
}
