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
        Book book = bookRepository.findByTitle(title);
        if(!book.getTitle().equals(title)) throw new BookNotFoundException("Book not found");
        return book;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);

    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
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
        String username = logoutAdminRequest.getUsername();

        for (Admin admin : adminRepository.findAll()) {
            if (admin.getUsername().equals(username)) {
                admin.setLogin(false);
                adminRepository.save(admin);
                LogoutAdminResponse logoutAdminResponse = new LogoutAdminResponse();
                logoutAdminResponse.setMessage("Successfully logged out");
                return logoutAdminResponse;
            }
        }

        throw new AdminLogoutException("Admin must be logged in before they can logout");
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
