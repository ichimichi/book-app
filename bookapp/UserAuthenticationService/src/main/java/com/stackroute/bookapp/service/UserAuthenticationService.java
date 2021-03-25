package com.stackroute.bookapp.service;

import com.stackroute.bookapp.exception.UserAlreadyExistException;
import com.stackroute.bookapp.exception.UserNotFoundException;
import com.stackroute.bookapp.model.User;

public interface UserAuthenticationService 
{
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistException;
}
