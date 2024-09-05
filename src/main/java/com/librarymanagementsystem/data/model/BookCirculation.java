package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class BookCirculation {
    private String id;
    private String title;
    private String author;
    private String bookUser;
    private LocalDateTime dateBorrowed;
    private LocalDateTime dueDate;
}
