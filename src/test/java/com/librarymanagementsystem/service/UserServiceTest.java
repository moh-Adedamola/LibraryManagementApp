package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.data.repositories.UserRepository;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import com.librarymanagementsystem.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void testThatUserCanBorrowBook(){
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

        BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
        borrowBookRequest.setTitle("no retreat no surrender");
        borrowBookRequest.setAuthor("crimson moh");
        borrowBookRequest.setGenre("action");
        BorrowBookResponse borrowBookResponse = userService.borrowBook(borrowBookRequest);
        assertNotNull(borrowBookResponse);
        assertThat(borrowBookResponse.getMessage()).isEqualTo("Borrowed book successfully");

    }

    @Test
    public void testThatUserCanFindAllBooks(){
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

        Book book = new Book();
        book.setTitle("no retreat no surrender");
        book.setAuthor("crimson moh");
        book.setGenre("action");

        Book book2 = new Book();
        book2.setTitle("no retreat no surrender");
        book2.setAuthor("crimson moh");
        book2.setGenre("action");

        List<Book> books = userService.findAllBooks();
        assertNotNull(books);
        assertThat(books.size()).isEqualTo(2);

    }

    @Test
    public void testThatUserCanFindBookByTitle(){
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

        Book foundBook = userService.findBookByTitle("hibiscus");
        assertNotNull(foundBook);
    }

    @Test
    public void testThatUserCanFindBookByAuthor(){
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

        List<Book> books =  userService.findBooksByAuthor("author");
        assertNotNull(books);

    }

    @Test
    public void testToLogoutUser(){
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

        LogoutUserRequest logoutUserRequest = new LogoutUserRequest();
        logoutUserRequest.setEmail("sekinat@gmail.com");
        LogoutUserResponse logoutUserResponse = userService.logoutUser(logoutUserRequest);
        assertNotNull(logoutUserResponse);
        assertThat(logoutUserResponse.getMessage()).isEqualTo("Successfully logged out");



    }

}