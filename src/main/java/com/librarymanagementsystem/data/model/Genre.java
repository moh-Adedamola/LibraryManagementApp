package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter

public class Genre {
    @Id

    private String id;
    private String name;
    private String description;
    private List<Book> books;

}
