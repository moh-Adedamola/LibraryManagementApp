package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Admin;
import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.data.model.User;
import com.librarymanagementsystem.data.repositories.BookRepository;
import com.librarymanagementsystem.data.repositories.UserRepository;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import com.librarymanagementsystem.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) {
        String email = loginUserRequest.getEmail().toLowerCase();
        String password = loginUserRequest.getPassword();

        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    user.setLogin(true);
                    userRepository.save(user);
                    LoginUserResponse loginResponse = new LoginUserResponse();
                    loginResponse.setMessage("Login Successful");
                    return loginResponse;
                } else {
                    throw new UsernameOrPasswordException("Invalid username or password");
                }
            }
        }
        throw new UserNotFoundException("User does not exist");
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateRegisterRequest(registerUserRequest);
        validateEmail(registerUserRequest.getEmail());
        User newUser = new User();
        newUser.setFirstName(registerUserRequest.getFirstName());
        newUser.setLastName(registerUserRequest.getLastName());
        newUser.setEmail(registerUserRequest.getEmail());
        newUser.setPassword(registerUserRequest.getPassword());
        userRepository.save(newUser);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration Successful");
        return registerUserResponse;


    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userRepository.findByEmail(updateUserRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User does not exist");
        }

        if (isUserLoggedIn()) {
            throw new UserLoginException("User must be logged in to update details");
        }


        if (updateUserRequest.getFirstName() == null || updateUserRequest.getFirstName().isEmpty()) {
                throw new InvalidRequestException("First name is required");
            }
        if (updateUserRequest.getLastName() == null || updateUserRequest.getLastName().isEmpty()) {
                throw new InvalidRequestException("Last name is required");
            }
        if (updateUserRequest.getPassword() == null || updateUserRequest.getPassword().isEmpty()) {
                throw new InvalidRequestException("Password is required");
            }

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword());

        userRepository.save(user);
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setMessage("Update details Successful");
        return updateUserResponse;
        }

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest borrowBookRequest) {
        Book book = bookRepository.findByTitle(borrowBookRequest.getTitle());
        if (book == null) {
            throw new BookNotFoundException("Book does not exist");
        }
        if (book.getStatus() != null && book.getStatus().isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book is already borrowed");
        }


        User user = userRepository.findByEmail(borrowBookRequest.getUserEmail());
        if (isUserLoggedIn()) {
            throw new UserLoginException("User must be logged in to borrow book");
        }
        book.setUser(user);
        bookRepository.save(book);
        BorrowBookResponse borrowBookResponse = new BorrowBookResponse();
        borrowBookResponse.setMessage("Borrowed book successfully");
        return borrowBookResponse;

    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByTitle(String title) {
        title = title.toLowerCase();
        Book book = bookRepository.findByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Book not found");
        }
        if(!book.getTitle().equals(title)) {
            throw new BookNotFoundException("Book not found");
        }
        return book;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {

        author = author.trim().toLowerCase();
        if (author.isEmpty()) {
            throw new InvalidAuthorException("No books found for this Author");
        }
        return bookRepository.findBooksByAuthor(author);

    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        genre = genre.trim().toLowerCase();
        if (genre.isEmpty()) {
            throw new InvalidGenreException("No books found for this Genre");
        }
        return bookRepository.findBooksByGenre(genre);
    }

    @Override
    public LogoutUserResponse logoutUser(LogoutUserRequest logoutUserRequest) {
        User user = userRepository.findByEmail(logoutUserRequest.getEmail().toLowerCase());

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        user.setLogin(false);
        userRepository.save(user);
        LogoutUserResponse logoutUserResponse = new LogoutUserResponse();
        logoutUserResponse.setMessage("Logout successful");
        return logoutUserResponse;



    }

    private boolean isUserLoggedIn() {
        return false;
    }


    private void validateRegisterRequest(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest.getFirstName().trim().isEmpty() || registerUserRequest.getLastName().trim().isEmpty()
        || registerUserRequest.getEmail().trim().isEmpty() || registerUserRequest.getPassword().trim().isEmpty()){
            throw new InvalidRegisterRequestException("Field cannot be empty");
        }
    }


    private void validateEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new UserAlreadyExistException("User with same email already exist");
            }
            if (!email.contains("@") || !email.endsWith(".com")) {
                throw new InvalidEmailException("invalid email format");

            }
        }

    }
}