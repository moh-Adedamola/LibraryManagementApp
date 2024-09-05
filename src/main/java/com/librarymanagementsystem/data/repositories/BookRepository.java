package com.librarymanagementsystem.data.repositories;

import com.librarymanagementsystem.data.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
