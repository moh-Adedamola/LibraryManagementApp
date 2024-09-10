package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("authors")

public class Author {
    @Id

    private String id;
    private String firstName;
    private String lastName;
    private List<Book> books;

}
