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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.FavouriteService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.FavouriteService.exception.BookNotFoundException;
import com.stackroute.bookapp.FavouriteService.exception.UserNotFoundException;
import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.service.FavouriteService;

import io.jsonwebtoken.Claims;

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
	public ResponseEntity<?> addBook(@RequestAttribute Claims claims,@RequestBody Book book){
		System.out.println(claims.getId());
		try {
		if (service.addBook(book,claims.getId()) ){
			return new ResponseEntity<Book>(book, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Oops something went wrong !! try again", HttpStatus.CONFLICT);
		}
		}catch(BookAlreadyExistsException e) {
			return new ResponseEntity<String>("Oops something went wrong !! try again", HttpStatus.CONFLICT);
		}
	}


	@DeleteMapping()
	public ResponseEntity<String> deleteBook(@RequestAttribute Claims claims,@RequestParam String bookId) {
		System.out.println(claims.getId());
		try {
		if (service.removeBook(bookId,claims.getId())) {
			return new ResponseEntity<String>("Successfully removed Book", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		}
		}catch(BookNotFoundException e) {
			return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/all")
	public ResponseEntity<String> deleteAllBooks(@RequestAttribute Claims claims) {
		try {
			service.deleteAllBooks(claims.getId());
			return new ResponseEntity<String>("Successfully deleted all books", HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>("Unable to purge please try again", HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping()
	public ResponseEntity<?> getAllBooksByUserId(@RequestAttribute Claims claims) {
//		return new ResponseEntity<String>("Ohh Yeahh!!Did It!!", HttpStatus.OK);
		try {
		List<Book> userBooks = service.getAllBooksByUserId(claims.getId());
		
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
