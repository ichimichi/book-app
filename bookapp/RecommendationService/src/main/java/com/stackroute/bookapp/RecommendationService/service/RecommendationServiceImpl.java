package com.stackroute.bookapp.RecommendationService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.model.Recommendation;
import com.stackroute.bookapp.RecommendationService.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	RecommendationRepository recommendationRepository;

	@Override
	public List<Book> getAllRecommendedBooks(String userId) throws Exception {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsByUserId(userId)) {
			Recommendation rec = recommendationRepository.findByUserId(userId);
			return rec.getBooks();
		} else {
			throw new Exception(userId);
		}
	}

	@Override
	public Book removeBookByUser(Book book, String userId) throws Exception {
		// TODO Auto-generated method stub
		Book deletedBook = null;
		if (recommendationRepository.existsByUserId(userId)) {
			Recommendation rec = recommendationRepository.findByUserId(userId);
			List bookList = rec.getBooks();
			
			Iterator iterator = bookList.listIterator();
			
			while (iterator.hasNext()) {
				Book b = (Book) iterator.next();
				if (b.getId().equals(book.getId())) {
					iterator.remove();
					deletedBook = b;
				}
			}
			rec.setBooks(bookList);
			recommendationRepository.save(rec);
			if(deletedBook!=null) {
				return deletedBook;	
			}else {
				throw new Exception("Book not found");
			}
			
		} else {
			throw new Exception(userId);
		}
	}

	@Override
	public Book addtoRecommendations(Book book, String userId) throws Exception {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsByUserId(userId)) {
			Recommendation rec = recommendationRepository.findByUserId(userId);
			List bookList = rec.getBooks();
			bookList.add(book);
			rec.setBooks(bookList);
			recommendationRepository.save(rec);
			return book;

		} else {
			Recommendation rec = new Recommendation();
			rec.setUserId(userId);
			List bookList = new ArrayList<>();
			bookList.add(book);
			rec.setBooks(bookList);
			recommendationRepository.insert(rec);
			return book;
		}
	}

}
