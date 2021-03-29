package com.stackroute.bookapp.RecommendationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.RecommendationService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.service.RecommendationServiceImpl;

import io.jsonwebtoken.Claims;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/recommendation")
public class RecommendationController {
	
	@Autowired
	RecommendationServiceImpl recommendationService;
	
	public RecommendationController() {
		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRecommendedBooks(@RequestAttribute Claims claims){
		System.out.println(claims.getId());
		try {
			return new ResponseEntity<List<Book>>(recommendationService.getAllRecommendedBooks(claims.getId()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> addtoRecommendations(@RequestAttribute Claims claims, @RequestBody Book book){
		System.out.println(claims.getId());
		try {
			return new ResponseEntity<Book>(recommendationService.addtoRecommendations(book, claims.getId()), HttpStatus.OK);
		}catch (BookAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> removeBookByUser(@RequestAttribute Claims claims, @RequestParam String bookId){
		System.out.println(claims.getId());
		try {
			return new ResponseEntity<Book>(recommendationService.removeBookByUser(bookId, claims.getId()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
