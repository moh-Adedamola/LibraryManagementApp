package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateBookRequest {
    private String title;
    private String author;
    private String genre;
    private String description;


}
