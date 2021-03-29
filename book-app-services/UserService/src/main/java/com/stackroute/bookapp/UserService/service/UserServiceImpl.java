package com.stackroute.bookapp.UserService.service;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.UserService.exception.UserAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	public User registerUser(User user) throws UserAlreadyExistsException {
		User savedUser = null;
		if (repository.existsById(user.getId())) {
			throw new UserAlreadyExistsException("User with ID" + user.getId() + "already exists");
		} else {
			user.setDob((java.sql.Date) new Date());
			savedUser = repository.insert(user);
			if (savedUser == null) {
				throw new UserAlreadyExistsException("User with ID" + user.getId() + "already exists");
			}
		}
		return savedUser;
	}

	public User updateUser(String id, User user) throws UserNotFoundException {

		try {
			User fecthedUser = repository.findById(id).get();
			fecthedUser.setName(user.getName());
			fecthedUser.setEmail(user.getEmail());
			fecthedUser.setPassword(user.getPassword());
			fecthedUser.setId(user.getId());

			repository.save(fecthedUser);
			return fecthedUser;

		} catch (NoSuchElementException exception) {

			throw new UserNotFoundException("User does not exists");
		}

	}

	public boolean deleteUser(String id) throws UserNotFoundException {
		boolean status = false;
		try {
			User fecthedUser = repository.findById(id).get();
			if (fecthedUser != null) {
				repository.delete(fecthedUser);
				status = true;
			}
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User does not exists");
		}
		return status;
	}
	public User getUserById(String id) throws UserNotFoundException {
		User fecthedUser = repository.findById(id).get();
		if (fecthedUser == null) {
			throw new UserNotFoundException("User does not exists");
		}
		return fecthedUser;
	}
}
