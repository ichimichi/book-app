package com.stackroute.bookapp.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.UserService.exception.UserAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.service.UserService;
import com.stackroute.bookapp.UserService.service.UserServiceImpl;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserServiceImpl service;
	
	public UserController() {
		
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody User user) {
		try {
			service.registerUser(user);
			return new ResponseEntity<String>("Created", HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable() String id, @RequestBody User user) {
		try {
			return new ResponseEntity<User>(service.updateUser(id, user), HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable() String id) {
		try {
			service.deleteUser(id);
			return new ResponseEntity<String>("Successfully Deleted User with id: " + id, HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable() String id) {
		try {
			return new ResponseEntity<User>(service.getUserById(id), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
	}
}
