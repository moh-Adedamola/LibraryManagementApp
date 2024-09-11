package com.librarymanagementsystem.data.repositories;

import com.librarymanagementsystem.data.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitleAndAuthor(String title, String author);

    Book findByTitle(String title);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByGenre(String genre);
}
