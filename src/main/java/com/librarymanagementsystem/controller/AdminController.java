package com.librarymanagementsystem.controller;

import com.librarymanagementsystem.dtos.request.AddBookRequest;
import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.responses.AddBookResponse;
import com.librarymanagementsystem.dtos.responses.ApiResponse;
import com.librarymanagementsystem.dtos.responses.LoginAdminResponse;
import com.librarymanagementsystem.dtos.responses.RegisterAdminResponse;
import com.librarymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
