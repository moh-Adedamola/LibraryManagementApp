package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Admin;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.data.model.User;
import com.librarymanagementsystem.data.repositories.AdminRepository;
import com.librarymanagementsystem.data.repositories.BookRepository;
import com.librarymanagementsystem.data.repositories.UserRepository;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;

import com.librarymanagementsystem.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


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
        Admin admin = adminRepository.findByUsername(loginAdminRequest.getUsername());
        if (admin == null) {
            throw new AdminNotFoundException("Admin does not exist");
        }
        if (!admin.getPassword().equals(loginAdminRequest.getPassword())) {
            throw new UsernameOrPasswordException("Invalid username or password");
        }
        LoginAdminResponse loginAdminResponse = new LoginAdminResponse();
        loginAdminResponse.setMessage("Login Successful");
        return loginAdminResponse;
    }



    @Override
    public AddBookResponse addBook(AddBookRequest addBookRequest) {
        validateBookRequest(addBookRequest.getTitle());
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



    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findByTitleAndAuthor(updateBookRequest.getTitle(), updateBookRequest.getAuthor());
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        book.setGenre(updateBookRequest.getGenre());
        book.setDescription(updateBookRequest.getDescription());
        bookRepository.save(book);
        UpdateBookResponse updateBookResponse = new UpdateBookResponse();
        updateBookResponse.setMessage("Book details updated successfully");
        return updateBookResponse;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();

    }

    @Override
    public Book findBookByTitle(String title) {
        title = title.trim().toLowerCase();
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        if(!book.getTitle().equals(title)) throw new BookNotFoundException("Book not found");
        return book;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new InvalidAuthorException("No books found for this Author");
        }
        author = author.trim().toLowerCase();
        return bookRepository.findBooksByAuthor(author);

    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new InvalidGenreException("No books found for this Genre");
        }
        genre = genre.trim().toLowerCase();
            return bookRepository.findBooksByGenre(genre);

    }

    @Override
    public DeleteBookResponse deleteBook(String id) {
        bookRepository.deleteById(id);
        DeleteBookResponse deleteBookResponse = new DeleteBookResponse();
        deleteBookResponse.setMessage("Book successfully deleted!");
        return deleteBookResponse;
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public LogoutAdminResponse logoutAdmin(LogoutAdminRequest logoutAdminRequest) {
        Admin admin = adminRepository.findByUsername(logoutAdminRequest.getUsername());

        if (admin == null) {
            throw new AdminNotFoundException("Admin not found");
        }

//        if (!admin.isLogin()) {
//            throw new AdminLogoutException("Admin must first be logged in");
//        }
        admin.setLogin(false);
        adminRepository.save(admin);
        LogoutAdminResponse logoutAdminResponse = new LogoutAdminResponse();
        logoutAdminResponse.setMessage("Logout successful");
        return logoutAdminResponse;


    }



    private void validateBookRequest(String title) {

            for (Book book : bookRepository.findAll()) {
                if (book.getTitle().equals(title)){
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
