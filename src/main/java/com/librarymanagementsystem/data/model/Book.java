package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("books")
public class Book {

    private String id;
    private String title;
    private List<Author> authors;
    private List<Category> categories;


}
