package com.stackroute.bookapp.FavouriteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookapp.FavouriteService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.FavouriteService.exception.BookNotFoundException;
import com.stackroute.bookapp.FavouriteService.exception.UserNotFoundException;
import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.service.FavouriteService;





@RestController
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
	@PostMapping("/{userId}")
	public ResponseEntity<?> addBook(@PathVariable String userId,@RequestBody Book book){
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
	public ResponseEntity<String> deleteBook(@PathVariable String userId,@RequestBody Book book) {
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
//	@ApiOperation(value="Delete All notes")
	@DeleteMapping("/all/{userId}")
	public ResponseEntity<String> deleteAllBooks(@PathVariable String userId) {
		try {
			service.deleteAllBooks(userId);
			return new ResponseEntity<String>("Successfully deleted all books", HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return new ResponseEntity<String>("Unable to purge please try again", HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Define a handler method which will update a specific note by reading the
	 * Serialized object from request body and save the updated note details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If the note updated successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP PUT
	 * method.
	 */
//	@ApiOperation(value="Update Note")


	
	
//	@ApiOperation(value="Get All notes by UserID")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllBooksByUserId(@PathVariable String userId) {
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

	/*
	 * Define a handler method which will show details of a specific note created by
	 * specific user. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the note found
	 * successfully. 2. 404(NOT FOUND) - If the note with specified noteId is not
	 * found. This handler method should map to the URL
	 * "/api/v1/note/{userId}/{noteId}" using HTTP GET method where "id" should be
	 * replaced by a valid reminderId without {}
	 * 
	 */
//	@ApiOperation(value="Get Note by UserId and noteId")
	
	
}
