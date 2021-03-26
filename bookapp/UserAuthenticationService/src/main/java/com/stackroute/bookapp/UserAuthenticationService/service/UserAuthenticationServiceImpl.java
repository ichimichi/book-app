package com.stackroute.bookapp.UserAuthenticationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.UserAuthenticationService.exception.UserAlreadyExistException;
import com.stackroute.bookapp.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserAuthenticationService.model.User;
import com.stackroute.bookapp.UserAuthenticationService.repository.UserAuthenticationRepository;

@Service
public class UserAuthenticationServiceImpl 
{
	@Autowired
	UserAuthenticationRepository repository;

	public UserAuthenticationServiceImpl(UserAuthenticationRepository repository)
	{
		this.repository = repository;
	}
	
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user=repository.findByUserIdAndUserPassword(userId, password);
		if(user ==null) {
			throw new UserNotFoundException("User is not found");
		}
		return user;
	}
	
	public boolean saveUser(User user) throws UserAlreadyExistException 
	{
		java.util.Optional<User> optional=repository.findById(user.getUserId());
		if(optional.isPresent())
		{
			throw new UserAlreadyExistException("user already exist");
		}
		repository.save(user);
		return Boolean.TRUE;
	}
}
