package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.Genre;
import com.librarymanagementsystem.data.repositories.AdminRepository;
import com.librarymanagementsystem.dtos.request.*;
import com.librarymanagementsystem.dtos.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

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

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("Book title");
        addBookRequest.setAuthor("Author");
        addBookRequest.setGenre("fiction");
        addBookRequest.setDescription("This is a fiction book");
        AddBookResponse addBookResponse = adminService.addBook(addBookRequest);
        assertNotNull(addBookResponse);
        assertThat(addBookResponse.getMessage()).isEqualTo("Book successfully added!");
    }



}
