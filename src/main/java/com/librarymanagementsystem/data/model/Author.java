package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("authors")

public class Author {
    private String id;
    private String firstName;
    private String lastName;
    private List<Book> books;

}
