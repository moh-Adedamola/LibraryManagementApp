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
    private boolean isAvailable;
    private boolean isBorrowed;
    private LocalDateTime dueDate;

}
