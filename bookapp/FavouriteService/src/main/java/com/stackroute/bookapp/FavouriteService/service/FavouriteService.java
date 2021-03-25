package com.stackroute.bookapp.FavouriteService.service;

import java.util.List;

import com.stackroute.bookapp.FavouriteService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.FavouriteService.exception.BookNotFoundException;
import com.stackroute.bookapp.FavouriteService.exception.UserNotFoundException;
import com.stackroute.bookapp.FavouriteService.model.Book;

public interface FavouriteService {

	
		boolean addBook(Book book,String userId) throws BookAlreadyExistsException;
		boolean removeBook(Book book,String UserId) throws BookNotFoundException;
		boolean deleteAllBooks(String userId)throws UserNotFoundException;
		List<Book>getAllBooksByUserId(String userId)throws UserNotFoundException;
		
}
