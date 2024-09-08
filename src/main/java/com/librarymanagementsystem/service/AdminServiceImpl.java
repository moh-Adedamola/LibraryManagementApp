package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Admin;

import com.librarymanagementsystem.data.repositories.AdminRepository;
import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.responses.LoginAdminResponse;

import com.librarymanagementsystem.dtos.responses.RegisterAdminResponse;
import com.librarymanagementsystem.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) {
        validateRegisteredAdmin(registerAdminRequest);
        validateAdminEmail(registerAdminRequest.getEmail());
        Admin newAdmin = new Admin();
        newAdmin.setFirstName(registerAdminRequest.getFirstName());
        newAdmin.setLastName(registerAdminRequest.getLastName());
        newAdmin.setUsername(registerAdminRequest.getUsername());
        newAdmin.setEmail(registerAdminRequest.getEmail());
        newAdmin.setPassword(registerAdminRequest.getPassword());
        adminRepository.save(newAdmin);
        RegisterAdminResponse registerAdminResponse = new RegisterAdminResponse();
        registerAdminResponse.setMessage("Admin Successfully registered!");
        return registerAdminResponse;
    }

    @Override
    public LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        String username = loginAdminRequest.getUsername();
        String password = loginAdminRequest.getPassword();

        for (Admin admin : adminRepository.findAll()) {
            if (admin.getUsername().equals(username)) {
                if (admin.getPassword().equals(password)) {
                    admin.setLogin(true);
                    adminRepository.save(admin);
                    LoginAdminResponse loginAdminResponse = new LoginAdminResponse();
                    loginAdminResponse.setMessage("Login Successful");
                    return loginAdminResponse;
                } else {
                    throw new UsernameOrPasswordException("Invalid username or password");
                }
            }
        }
        throw new UserNotFoundException("User does not exist");
    }

    private void validateAdminEmail(String email) {
        if (!email.contains("@") || !email.endsWith(".com")){
            throw new InvalidEmailException("invalid email format");
        }
        for (Admin admin : adminRepository.findAll()) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                throw new UserAlreadyExistException("Admin with same email already exist");
            }
        }
    }


    private void validateRegisteredAdmin(RegisterAdminRequest registerAdminRequest) {

        if (registerAdminRequest.getFirstName().trim().isEmpty() || registerAdminRequest.getLastName().trim().isEmpty()
                || registerAdminRequest.getEmail().trim().isEmpty() || registerAdminRequest.getPassword().trim().isEmpty()){
            throw new InvalidRegisterRequestException("Field cannot be empty");
        }
    }
}
