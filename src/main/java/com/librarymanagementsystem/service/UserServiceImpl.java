package com.librarymanagementsystem.service;

import com.librarymanagementsystem.data.model.User;
import com.librarymanagementsystem.data.repositories.UserRepository;
import com.librarymanagementsystem.dtos.request.LoginUserRequest;
import com.librarymanagementsystem.dtos.request.RegisterUserRequest;
import com.librarymanagementsystem.dtos.request.UpdateUserRequest;
import com.librarymanagementsystem.dtos.responses.LoginUserResponse;
import com.librarymanagementsystem.dtos.responses.RegisterUserResponse;
import com.librarymanagementsystem.dtos.responses.UpdateUserResponse;
import com.librarymanagementsystem.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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

        if (!isUserLoggedIn()) {
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

    private boolean isUserLoggedIn() {
        return true;
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