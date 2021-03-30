package com.stackroute.bookapp.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.UserService.exception.InterestAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserAlreadyExistsException;
import com.stackroute.bookapp.UserService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.service.UserServiceImpl;

import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserServiceImpl service;

	public UserController() {

	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user, @RequestAttribute Claims claims) {
		try {
			user.setEmail(claims.getId());
			service.registerUser(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user, @RequestAttribute Claims claims) {
		try {
			return new ResponseEntity<User>(service.updateUser(claims.getId(), user), HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam String id, @RequestAttribute Claims claims) {
		try {
			service.deleteUser(claims.getId());
			return new ResponseEntity<String>("Successfully Deleted User with id: " + claims.getId(), HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<?> getUserById(@RequestAttribute Claims claims) {
		try {
			return new ResponseEntity<User>(service.getUserById(claims.getId()), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/interest")
	public ResponseEntity<?> addToInterest(@RequestParam String name, @RequestAttribute Claims claims) {
		try {
			User user = service.addToInterests(claims.getId(), name);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (InterestAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/interest")
	public ResponseEntity<?> getAllInterest(@RequestAttribute Claims claims) {
		try {
			List interestList = service.getAllInterest(claims.getId());
			return new ResponseEntity<List>(interestList, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
