package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class LoginAdminRequest {
    private String username;
    private String password;
}
