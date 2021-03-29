package com.stackroute.bookapp.FavouriteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.FavouriteService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.FavouriteService.exception.BookNotFoundException;
import com.stackroute.bookapp.FavouriteService.exception.UserNotFoundException;
import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.service.FavouriteService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/fav")
public class FavouriteController {

	
	@Autowired
	FavouriteService service;

	public FavouriteController(FavouriteService service) {
		super();
		this.service = service;
	}
	
//	@GetMapping("/")
//	public ResponseEntity<?> display()
//	{
//		return new ResponseEntity<String>("Ohh Yeahh!!Did It!!", HttpStatus.OK);
//		
//		
//	}
	
	@PostMapping()
	public ResponseEntity<?> addBook(@RequestParam String userId,@RequestBody Book book){
		try {
		if (service.addBook(book,userId)) {
			return new ResponseEntity<Book>(book, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Oops something went wrong !! try again", HttpStatus.CONFLICT);
		}
		}catch(BookAlreadyExistsException e) {
			return new ResponseEntity<String>("Oops something went wrong !! try again", HttpStatus.CONFLICT);
		}
	}


	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteBook(@RequestParam String userId,@RequestBody Book book) {
		try {
		if (service.removeBook(book,userId)) {
			return new ResponseEntity<String>("Successfully removed Book", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		}
		}catch(BookNotFoundException e) {
			return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/all/{userId}")
	public ResponseEntity<String> deleteAllBooks(@RequestParam String userId) {
		try {
			service.deleteAllBooks(userId);
			return new ResponseEntity<String>("Successfully deleted all books", HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>("Unable to purge please try again", HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllBooksByUserId(@RequestParam String userId) {
//		return new ResponseEntity<String>("Ohh Yeahh!!Did It!!", HttpStatus.OK);
		try {
		List<Book> userBooks = service.getAllBooksByUserId(userId);
		
		if (userBooks != null) {
			return new ResponseEntity<List<Book>>(userBooks, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("You don't have any book added in your list", HttpStatus.OK);
		}}
		catch(UserNotFoundException e) {
			return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
		}
	}

	

	
}
