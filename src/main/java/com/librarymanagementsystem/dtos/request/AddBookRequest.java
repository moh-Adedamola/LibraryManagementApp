package com.librarymanagementsystem.dtos.request;

import com.librarymanagementsystem.data.model.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AddBookRequest {
    private String title;
    private String author;
    private String genre;
    private String description;



}
