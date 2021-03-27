package com.stackroute.bookapp.RecommendationService.service;

import java.util.List;

import com.stackroute.bookapp.RecommendationService.exception.BookNotFoundException;
import com.stackroute.bookapp.RecommendationService.exception.UserNotFoundException;
import com.stackroute.bookapp.RecommendationService.model.Book;

public interface RecommendationService {
	List<Book> getAllRecommendedBooks(String userId) throws UserNotFoundException;

	Book removeBookByUser(Book book, String userId) throws BookNotFoundException, UserNotFoundException;

	Book addtoRecommendations(Book book, String userId);
}
