package com.stackroute.bookapp.RecommendationService.service;

import java.util.List;

import com.stackroute.bookapp.RecommendationService.model.Book;

public interface RecommendationService {
	List<Book> getAllRecommendedBooks(String userId) throws Exception;

	Book removeBookByUser(Book book, String userId) throws Exception;

	Book addtoRecommendations(Book book, String userId) throws Exception;
}
