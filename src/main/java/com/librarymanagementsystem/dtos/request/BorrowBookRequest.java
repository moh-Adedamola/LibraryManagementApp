package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BorrowBookRequest {
    private String userEmail;
    private String title;
    private String author;
    private String genre;



}
