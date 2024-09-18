package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import com.librarymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private  final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdminRequest registerAdminRequest) {
        try {
            RegisterAdminResponse registerAdminResponse = adminService.registerAdmin(registerAdminRequest);
            return new ResponseEntity<>(new ApiResponse(true, registerAdminResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginAdminRequest loginAdminRequest) {
        try {
            LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
            return new ResponseEntity<>(new ApiResponse(true, loginAdminResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest addBookRequest) {
        try {
            AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
            return new ResponseEntity<>(new ApiResponse(true, addBookResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateBook")
    public ResponseEntity<?> updateBook(@RequestBody UpdateBookRequest updateBookRequest) {
        try {
            UpdateBookResponse updateBookResponse = adminService.updateBook(updateBookRequest);
            return new ResponseEntity<>(new ApiResponse(true, updateBookResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBookByTitle")
    public ResponseEntity<?> findBookByTitle(@RequestBody GetBookByTitleRequest getBookByTitleRequest ) {
        try {
            Book book = adminService.findBookByTitle(getBookByTitleRequest.getTitle());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBooksByAuthor")
    public ResponseEntity<?> findBooksByAuthor(@RequestBody GetBooksByAuthorRequest getBooksByAuthorRequest ) {
        try {
            List<Book> book = adminService.findBooksByAuthor(getBooksByAuthorRequest.getAuthor());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBooksByGenre")
    public ResponseEntity<?> findBooksByGenre(@RequestBody GetBooksByGenreRequest getBooksByGenreRequest ) {
        try {
            List<Book> book = adminService.findBooksByGenre(getBooksByGenreRequest.getGenre());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<?> deleteBook(@RequestBody DeleteBookRequest deleteBookRequest ) {
        try {
            DeleteBookResponse book = adminService.deleteBook(deleteBookRequest.getId());
            return new ResponseEntity<>(new ApiResponse(true, book), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logoutAdmin")
    public ResponseEntity<?> logoutAdmin(@RequestBody LogoutAdminRequest logoutAdminRequest) {
        try {
            LogoutAdminResponse logoutAdminResponse = adminService.logoutAdmin(logoutAdminRequest);
            return new ResponseEntity<>(new ApiResponse(true, logoutAdminResponse), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/findAllBooks")
//    public ResponseEntity<?> findAllBooks(@RequestBody GetBooksRequest getBooksRequest) {
//        try {
//            List<Book> books = adminService.findAllBooks();
//            return new ResponseEntity<>(new ApiResponse(true, books), HttpStatus.CREATED);
//        } catch (Exception exception) {
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }

}
