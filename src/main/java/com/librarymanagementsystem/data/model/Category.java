package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Category {
    private String id;
    private String name;
    private List<Book> books;

}
