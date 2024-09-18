package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import com.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        try{
            LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,loginUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        try{
            UpdateUserResponse updateUserResponse = userService.updateUser(updateUserRequest);
            return new ResponseEntity<>(new ApiResponse(true,updateUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowBookRequest borrowBookRequest) {
        try{
            BorrowBookResponse borrowBookResponse = userService.borrowBook(borrowBookRequest);
            return new ResponseEntity<>(new ApiResponse(true,borrowBookResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBookByTitle")
    public ResponseEntity<?> findBookByTitle(@RequestBody GetBookByTitleRequest getBookByTitleRequest ) {
        try {
            Book book = userService.findBookByTitle(getBookByTitleRequest.getTitle());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBooksByAuthor")
    public ResponseEntity<?> findBooksByAuthor(@RequestBody GetBooksByAuthorRequest getBooksByAuthorRequest ) {
        try {
            List<Book> book = userService.findBooksByAuthor(getBooksByAuthorRequest.getAuthor());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBooksByGenre")
    public ResponseEntity<?> findBooksByGenre(@RequestBody GetBooksByGenreRequest getBooksByGenreRequest ) {
        try {
            List<Book> book = userService.findBooksByGenre(getBooksByGenreRequest.getGenre());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<?> logoutUser(@RequestBody LogoutUserRequest logoutUserRequest) {
        try {
            LogoutUserResponse logoutUserResponse = userService.logoutUser(logoutUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, logoutUserResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
