package com.librarymanagementsystem.service;

import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.responses.LoginAdminResponse;
import com.librarymanagementsystem.dtos.responses.RegisterAdminResponse;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest);

    LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest);
}
