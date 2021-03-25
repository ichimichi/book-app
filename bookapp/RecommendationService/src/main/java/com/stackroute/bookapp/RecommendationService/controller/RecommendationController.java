package com.stackroute.bookapp.RecommendationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.service.RecommendationServiceImpl;

@RestController
public class RecommendationController {
	
	@Autowired
	RecommendationServiceImpl recommendationService;
	
	public RecommendationController() {
		
	}
	
	@GetMapping("/api/v1/recommendation")
	public ResponseEntity<?> getAllRecommendedBooks(@RequestParam String userId){
		try {
			return new ResponseEntity<List<Book>>(recommendationService.getAllRecommendedBooks(userId), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/api/v1/recommendation")
	public ResponseEntity<?> addtoRecommendations(@RequestParam String userId, @RequestBody Book book){
		try {
			return new ResponseEntity<Book>(recommendationService.addtoRecommendations(book, userId), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/v1/recommendation")
	public ResponseEntity<?> removeBookByUser(@RequestParam String userId, @RequestBody Book book){
		try {
			return new ResponseEntity<Book>(recommendationService.removeBookByUser(book, userId), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
