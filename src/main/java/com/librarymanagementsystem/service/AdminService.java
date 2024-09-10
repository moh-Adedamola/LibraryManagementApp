package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dtos.request.AddBookRequest;
import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.request.UpdateBookRequest;
import com.librarymanagementsystem.dtos.responses.AddBookResponse;
import com.librarymanagementsystem.dtos.responses.LoginAdminResponse;
import com.librarymanagementsystem.dtos.responses.RegisterAdminResponse;
import com.librarymanagementsystem.dtos.responses.UpdateBookResponse;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest);

    LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest);

    AddBookResponse addBook(AddBookRequest addBookRequest);

//    UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest);
}
