package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginUserRequest {
    private String email;
    private String password;
}
