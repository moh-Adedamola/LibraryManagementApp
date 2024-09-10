package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document("LibraryAppAdmin")

public class Admin {
    @Id

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLogin;

}
