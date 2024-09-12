package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Book;
import com.librarymanagementsystem.data.model.Genre;
import com.librarymanagementsystem.data.repositories.AdminRepository;
import com.librarymanagementsystem.data.repositories.BookRepository;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        adminRepository.deleteAll();
    }

    @Test
    public void testRegisterAdmin() {
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("ademola");
        registerAdminRequest.setLastName("fakayode");
        registerAdminRequest.setUsername("ademola");
        registerAdminRequest.setEmail("ademola@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");

    }

    @Test
    public void testToLoginAdmin(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("yinka");
        registerAdminRequest.setLastName("obanla");
        registerAdminRequest.setUsername("obanla");
        registerAdminRequest.setEmail("obanla@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("obanla");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");


    }

    @Test
    public void testThatAdminCanAddBook(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("yusuf");
        registerAdminRequest.setLastName("falade");
        registerAdminRequest.setUsername("faladex");
        registerAdminRequest.setEmail("faladex@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("faladex");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("my book");
        addBookRequest.setAuthor("bro");
        addBookRequest.setGenre("fiction");
        addBookRequest.setDescription("This is a fiction book");
        AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
        assertNotNull(addBookResponse);
        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");
    }

    @Test
    public void testThatAdminCanUpdateBook(){

        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("john");
        registerAdminRequest.setLastName("thomas");
        registerAdminRequest.setUsername("thomas");
        registerAdminRequest.setEmail("faladex@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("thomas");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("new book");
        addBookRequest.setAuthor("bro");
        addBookRequest.setGenre("fiction");
        addBookRequest.setDescription("This is a fiction book");
        AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
        assertNotNull(addBookResponse);
        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");

        UpdateBookRequest updateBookRequest = new UpdateBookRequest();
        updateBookRequest.setGenre("updated genre");
        updateBookRequest.setDescription("Updated description");
        UpdateBookResponse updateBookResponse = adminService.updateBook(updateBookRequest);
        assertNotNull(updateBookResponse);
        assertThat(updateBookResponse.getMessage()).isEqualTo("Book details updated successfully");


    }

    @Test
    public void testThatAdminCanViewAllBooks(){
            RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
            registerAdminRequest.setFirstName("yusuf");
            registerAdminRequest.setLastName("falade");
            registerAdminRequest.setUsername("faladex");
            registerAdminRequest.setEmail("faladex@gmail.com");
            registerAdminRequest.setPassword("1234");
            RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
            assertNotNull(response);
            assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


            LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
            loginAdminRequest.setUsername("faladex");
            loginAdminRequest.setPassword("1234");
            LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
            assertNotNull(loginAdminResponse);
            assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

            AddBookRequest addBookRequest = new AddBookRequest();
            addBookRequest.setTitle("new book");
            addBookRequest.setAuthor("bro");
            addBookRequest.setGenre("fiction");
            addBookRequest.setDescription("This is a fiction book");
            AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
            assertNotNull(addBookResponse);
            assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");

            AddBookRequest addBookRequest2 = new AddBookRequest();
            addBookRequest2.setTitle("my book2");
            addBookRequest2.setAuthor("bro2");
            addBookRequest2.setGenre("fiction");
            addBookRequest2.setDescription("This is a fiction book");
            AddBookResponse addBookResponse2 = adminService.addBook(addBookRequest2);
            assertNotNull(addBookResponse2);
            assertThat(addBookResponse2.getMessage()).isEqualTo("Book successfully added!");

            List<Book> books =  adminService.findAllBooks();
            assertNotNull(books);
            assertEquals(books.size(),2);
    }
    @Test
    public void testThatAdminCanFindBookByTitle(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("yusuf");
        registerAdminRequest.setLastName("falade");
        registerAdminRequest.setUsername("faladex");
        registerAdminRequest.setEmail("faladex@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("faladex");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("hibiscus");
        addBookRequest.setAuthor("bro");
        addBookRequest.setGenre("fiction");
        addBookRequest.setDescription("This is a fiction book");
        AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
        assertNotNull(addBookResponse);
        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");

        Book foundBook = adminService.findBookByTitle("hibiscus");
        assertNotNull(foundBook);

    }
    @Test
    public void testThatAdminCanDeleteBook(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("mo");
        registerAdminRequest.setLastName("sallah");
        registerAdminRequest.setUsername("sallah");
        registerAdminRequest.setEmail("mosallah@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("sallah");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

//        AddBookRequest addBookRequest = new AddBookRequest();
//        addBookRequest.setTitle("lonely");
//        addBookRequest.setAuthor("bro");
//        addBookRequest.setGenre("fiction");
//        addBookRequest.setDescription("This is a fiction book");
//        AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
//        assertNotNull(addBookResponse);
//        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");

        Book book = adminService.findBookById("66e23ae62c426c1c4917784a");
        assertNotNull(book);

        adminService.deleteBook("66e23ae62c426c1c4917784a");
        assertEquals(0,bookRepository.count());
    }

    @Test
    public void testToLogoutAdmin(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("yinka");
        registerAdminRequest.setLastName("obanla");
        registerAdminRequest.setUsername("obanla");
        registerAdminRequest.setEmail("obanla@gmail.com");
        registerAdminRequest.setPassword("1234");
        RegisterAdminResponse response = adminService.registerAdmin(registerAdminRequest);
        assertNotNull(response);
        assertThat(response.getMessage()).isEqualTo("Admin Successfully registered!");


        LoginAdminRequest loginAdminRequest = new LoginAdminRequest();
        loginAdminRequest.setUsername("obanla");
        loginAdminRequest.setPassword("1234");
        LoginAdminResponse loginAdminResponse = adminService.loginAdmin(loginAdminRequest);
        assertNotNull(loginAdminResponse);
        assertThat(loginAdminResponse.getMessage()).isEqualTo("Login Successful");

        LogoutAdminRequest logoutAdminRequest = new LogoutAdminRequest();
        logoutAdminRequest.setUsername("obanla");
        LogoutAdminResponse logoutAdminResponse = adminService.logoutAdmin(logoutAdminRequest);
        assertNotNull(logoutAdminResponse);
        assertThat(logoutAdminResponse.getMessage()).isEqualTo("Successfully logged out");



    }

}
