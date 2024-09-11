package com.librarymanagementsystem.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GetBooksByGenreRequest {
    private String genre;
}
