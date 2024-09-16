package com.librarymanagementsystem.data.repositories;

import com.librarymanagementsystem.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


    User findByEmail(String email);
}
