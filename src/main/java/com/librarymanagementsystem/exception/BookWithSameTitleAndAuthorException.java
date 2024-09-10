package com.librarymanagementsystem.exception;

public class BookWithSameTitleAndAuthorException extends RuntimeException {
    public BookWithSameTitleAndAuthorException(String message) {
        super(message);
    }
}
