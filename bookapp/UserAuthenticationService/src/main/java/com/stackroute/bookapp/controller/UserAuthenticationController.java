package com.stackroute.bookapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.exception.*;

import com.stackroute.bookapp.model.User;
import com.stackroute.bookapp.service.UserAuthenticationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthenticationController 
{
	@Autowired
	UserAuthenticationService service;

	public UserAuthenticationController(UserAuthenticationService authicationService) {
		this.service = authicationService;
	}
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		try {
			service.saveUser(user);
			return new ResponseEntity<String>("Created", HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {
			service.findByUserIdAndPassword(user.getUserId(), user.getUserPassword());
			return new ResponseEntity<String>(getToken(user.getUserId(), user.getUserPassword()), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	public String getToken(String userId, String password) throws Exception {
		return Jwts.builder().setId(userId).setSubject(password).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

	}
}
