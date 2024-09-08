package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterAdminRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
