package com.stackroute.bookapp.RecommendationService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	RecommendationRepository recommendationRepository;

	@Override
	public List<Book> getAllRecommendedBooks(String userId) throws Exception {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsById(userId)) {

		} else {
			throw new Exception(userId);
		}
		return null;
	}

	@Override
	public Book removeBookByUser(Book book, String userId) throws Exception {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsById(userId)) {

		} else {
			throw new Exception(userId);
		}
		return null;
	}

	@Override
	public Book addtoRecommendations(Book book, String userId) throws Exception {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsById(userId)) {

		} else {
			throw new Exception(userId);
		}
		return null;
	}

}
