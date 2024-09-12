package com.librarymanagementsystem.data.repositories;

import com.librarymanagementsystem.data.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByUsername(String username);
}
