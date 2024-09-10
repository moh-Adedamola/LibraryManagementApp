package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Admin;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.data.repositories.AdminRepository;
import com.librarymanagementsystem.data.repositories.BookRepository;
import com.librarymanagementsystem.dtos.request.AddBookRequest;
import com.librarymanagementsystem.dtos.request.LoginAdminRequest;
import com.librarymanagementsystem.dtos.request.RegisterAdminRequest;
import com.librarymanagementsystem.dtos.request.UpdateBookRequest;
import com.librarymanagementsystem.dtos.responses.AddBookResponse;
import com.librarymanagementsystem.dtos.responses.LoginAdminResponse;

import com.librarymanagementsystem.dtos.responses.RegisterAdminResponse;
import com.librarymanagementsystem.dtos.responses.UpdateBookResponse;
import com.librarymanagementsystem.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest registerAdminRequest) {
        validateRegisteredAdmin(registerAdminRequest);
        validateAdminEmail(registerAdminRequest.getEmail());
        Admin newAdmin = new Admin();
        newAdmin.setFirstName(registerAdminRequest.getFirstName());
        newAdmin.setLastName(registerAdminRequest.getLastName());
        newAdmin.setUsername(registerAdminRequest.getUsername());
        newAdmin.setEmail(registerAdminRequest.getEmail());
        newAdmin.setPassword(registerAdminRequest.getPassword());
        adminRepository.save(newAdmin);
        RegisterAdminResponse registerAdminResponse = new RegisterAdminResponse();
        registerAdminResponse.setMessage("Admin Successfully registered!");
        return registerAdminResponse;
    }

    @Override
    public LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        String username = loginAdminRequest.getUsername();
        String password = loginAdminRequest.getPassword();

        for (Admin admin : adminRepository.findAll()) {
            if (admin.getUsername().equals(username)) {
                if (admin.getPassword().equals(password)) {
                    admin.setLogin(true);
                    adminRepository.save(admin);
                    LoginAdminResponse loginAdminResponse = new LoginAdminResponse();
                    loginAdminResponse.setMessage("Login Successful");
                    return loginAdminResponse;
                } else {
                    throw new UsernameOrPasswordException("Invalid username or password");
                }
            }
        }
        throw new UserNotFoundException("User does not exist");
    }

    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        validateBookRequest(addBookRequest.getTitle(), addBookRequest.getAuthor());
        Book newBook = new Book();
        newBook.setTitle(addBookRequest.getTitle());
        newBook.setAuthor(addBookRequest.getAuthor());
        newBook.setGenre(addBookRequest.getGenre());
        newBook.setDescription(addBookRequest.getDescription());
        bookRepository.save(newBook);
        AddBookResponse addBookResponse = new AddBookResponse();
        addBookResponse.setMessage("Book successfully added!");
        return addBookResponse;


    }



//    @Override
//    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
//        validateBookRequest(updateBookRequest.getTitle(), updateBookRequest.getAuthor());
//        Book bookToUpdate = bookRepository.findByTitleAndAuthor(updateBookRequest.getTitle(), updateBookRequest.getAuthor());
//
//        if (bookToUpdate == null) {
//            throw new BookNotFoundException("Book not found");
//        }
//
//        bookToUpdate.setGenre(updateBookRequest.getGenre());
//        bookToUpdate.setGenre(updateBookRequest.getGenre());
//        bookToUpdate.setDescription(updateBookRequest.getDescription());
//
//        bookRepository.save(bookToUpdate);
//
//        UpdateBookResponse updateBookResponse = new UpdateBookResponse();
//        updateBookResponse.setMessage("Book successfully updated!");
//
//        return updateBookResponse;
//
//    }

    private void validateBookRequest(String title,  String author) {
            for (Book book : bookRepository.findAll()) {
                if (book.getTitle().equals(title) && book.getAuthor().equals(author)){
                    throw new BookWithSameTitleAndAuthorException("Book with same title and author already exists");
                }

            }
    }

    private void validateAdminEmail(String email) {
        if (!email.contains("@") || !email.endsWith(".com")){
            throw new InvalidEmailException("invalid email format");
        }
        for (Admin admin : adminRepository.findAll()) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                throw new UserAlreadyExistException("Admin with same email already exist");
            }
        }
    }


    private void validateRegisteredAdmin(RegisterAdminRequest registerAdminRequest) {

        if (registerAdminRequest.getFirstName().trim().isEmpty() || registerAdminRequest.getLastName().trim().isEmpty()
                || registerAdminRequest.getEmail().trim().isEmpty() || registerAdminRequest.getPassword().trim().isEmpty()){
            throw new InvalidRegisterRequestException("Field cannot be empty");
        }
    }
}
