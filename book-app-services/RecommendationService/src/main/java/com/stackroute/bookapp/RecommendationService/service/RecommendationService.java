package com.stackroute.bookapp.RecommendationService.service;

import java.util.List;

import com.stackroute.bookapp.RecommendationService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.RecommendationService.exception.BookNotFoundException;
import com.stackroute.bookapp.RecommendationService.exception.NoRecommendationsExists;
import com.stackroute.bookapp.RecommendationService.exception.RecommendationAlreadyExists;
import com.stackroute.bookapp.RecommendationService.exception.UserNotFoundException;
import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.model.Recommendation;

public interface RecommendationService {
	List<Recommendation> getAllRecommendedBooks(String userId) throws NoRecommendationsExists;

	boolean removeBookByUser(String bookId, String userId) throws BookNotFoundException, UserNotFoundException;

	Book addtoRecommendations(Book book, String userId) throws RecommendationAlreadyExists;
}
