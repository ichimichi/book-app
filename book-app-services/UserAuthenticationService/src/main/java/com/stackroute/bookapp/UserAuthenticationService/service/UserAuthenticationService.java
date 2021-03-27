package com.stackroute.bookapp.UserAuthenticationService.service;

import com.stackroute.bookapp.UserAuthenticationService.exception.UserAlreadyExistException;
import com.stackroute.bookapp.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserAuthenticationService.model.User;

public interface UserAuthenticationService 
{
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistException;
}
