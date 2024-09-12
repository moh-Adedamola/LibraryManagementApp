package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.dtos.request.AddBookRequest;
import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.request.UpdateBookRequest;
import com.librarymanagementsystem.dtos.responses.*;

import java.util.List;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest);

    LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest);

    AddBookResponse addBook(AddBookRequest addBookRequest);

    UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest);

    List<Book> findAllBooks();

    Book findBookByTitle(String myBook);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByGenre(String genre);

    DeleteBookResponse deleteBook(String id);

    Book findBookById(String lonely);
}
