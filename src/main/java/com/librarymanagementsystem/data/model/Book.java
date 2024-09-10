package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("books")
public class Book {
    @Id

    private String id;
    private String title;
    private String author;
    private String genre;
    private String description;
    private Status status;

}
