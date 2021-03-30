package com.stackroute.bookapp.UserService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.UserService.exception.InterestAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	public User registerUser(User user) throws UserAlreadyExistsException {
		User savedUser = null;
		if (repository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("User with ID" + user.getEmail() + "already exists");
		} else {
			savedUser = repository.insert(user);
			if (savedUser == null) {
				throw new UserAlreadyExistsException("User with ID" + user.getEmail() + "already exists");
			}
		}
		return savedUser;
	}

	public User updateUser(String email, User user) throws UserNotFoundException {

		try {
			User fecthedUser = repository.findByEmail(email);
			fecthedUser.setName(user.getName());
			fecthedUser.setDob(user.getDob());

			repository.save(fecthedUser);
			return fecthedUser;

		} catch (NoSuchElementException exception) {

			throw new UserNotFoundException("User does not exists");
		}

	}

	public boolean deleteUser(String email) throws UserNotFoundException {
		boolean status = false;
		try {
			User fecthedUser = repository.findByEmail(email);
			if (fecthedUser != null) {
				repository.delete(fecthedUser);
				status = true;
			}
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User does not exists");
		}
		return status;
	}

	public User getUserById(String email) throws UserNotFoundException {
		User fecthedUser = repository.findByEmail(email);
		if (fecthedUser == null) {
			throw new UserNotFoundException("User does not exists");
		}
		return fecthedUser;
	}

	public User addToInterests(String email, String interest) throws InterestAlreadyExistsException, UserNotFoundException {
		if (repository.existsByEmail(email)) {
			User user = repository.findByEmail(email);
			List<String> interestList = user.getInterests();
			if(interestList==null) {
				interestList = new ArrayList<String>();
			}
			
			Iterator iterator = interestList.iterator();
			String interestDb;
			while (iterator.hasNext()) {

				interestDb = (String) (iterator.next());
				if (interestDb.equals(interest)) {
					throw new InterestAlreadyExistsException("Interest Already Exists");
				}
			}

			interestList.add(interest);
			user.setInterests(interestList);
			repository.save(user);
			return user;

		} else {
			throw new UserNotFoundException("user with email "+ email+" does not exists");
		}
	}

	@Override
	public List<String> getAllInterest(String email) throws UserNotFoundException {
		User fecthedUser = repository.findByEmail(email);
		if (fecthedUser == null) {
			throw new UserNotFoundException("User does not exists");
		}
		return fecthedUser.getInterests();
	}
}
