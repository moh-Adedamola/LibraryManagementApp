package com.librarymanagementsystem.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ApiResponse {
    boolean success;
    Object response;
}
