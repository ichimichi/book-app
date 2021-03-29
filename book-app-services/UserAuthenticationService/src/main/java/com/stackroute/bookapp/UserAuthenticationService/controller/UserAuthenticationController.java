package com.stackroute.bookapp.UserAuthenticationService.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.UserAuthenticationService.exception.*;
import com.stackroute.bookapp.UserAuthenticationService.model.Payload;
import com.stackroute.bookapp.UserAuthenticationService.model.User;
import com.stackroute.bookapp.UserAuthenticationService.service.UserAuthenticationServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController {
	@Autowired
	UserAuthenticationServiceImpl service;

	public UserAuthenticationController() {

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		try {
			service.saveUser(user);
			user.setPassword(null);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			User userDB = service.findByEmailAndPassword(user.getEmail(), user.getPassword());
			userDB.setPassword(null);
			Payload payload = new Payload();
			payload.setToken(getToken(user.getEmail(), user.getPassword()));
			payload.setUser(userDB);
			return new ResponseEntity<Payload>(payload, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	public String getToken(String id, String password) throws Exception {
		return Jwts.builder().setId(id).setSubject(password).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

	}
}
