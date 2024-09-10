package com.librarymanagementsystem.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter

public class Status {
    @Id
    private String id;
    private String name;
    private String description;
    private boolean isAvailable;
    private boolean isBorrowable;
    private LocalDateTime dueDate;

}
