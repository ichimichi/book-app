package com.stackroute.bookapp.UserService.service;

import java.util.List;

import com.stackroute.bookapp.UserService.exception.InterestAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserService.model.User;

public interface UserService {
	
	User registerUser(User user) throws UserAlreadyExistsException;
    User updateUser(String id,User user) throws UserNotFoundException;
    boolean deleteUser(String id) throws UserNotFoundException;
    User getUserById(String id) throws UserNotFoundException;
    User addToInterests(String email, String interest) throws InterestAlreadyExistsException, UserNotFoundException;
    List<String> getAllInterest(String email) throws UserNotFoundException;
}
