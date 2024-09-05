package com.librarymanagementsystem.data.repositories;

import com.librarymanagementsystem.data.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
