package com.stackroute.bookapp.RecommendationService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.RecommendationService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.RecommendationService.exception.BookNotFoundException;
import com.stackroute.bookapp.RecommendationService.exception.NoRecommendationsExists;
import com.stackroute.bookapp.RecommendationService.exception.RecommendationAlreadyExists;
import com.stackroute.bookapp.RecommendationService.exception.UserNotFoundException;
import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.model.Recommendation;
import com.stackroute.bookapp.RecommendationService.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	RecommendationRepository recommendationRepository;

	@Override
	public List<Recommendation> getAllRecommendedBooks(String userId) throws NoRecommendationsExists {
		List rec = recommendationRepository.findAll();
		if (rec.size() == 0) {
			throw new NoRecommendationsExists("No Recommendation exists yet");
		}
		return rec;
	}

	@Override
	public boolean removeBookByUser(String bookId, String userId) throws BookNotFoundException, UserNotFoundException {
		// TODO Auto-generated method stub
		String deletedUser = null;
		if (recommendationRepository.existsByBookId(bookId)) {
			Recommendation rec = recommendationRepository.findByBookId(bookId);
			List userList = rec.getUsers();

			Iterator iterator = userList.listIterator();

			while (iterator.hasNext()) {
				String userIdDb = (String) iterator.next();
				if (userIdDb.equals(userId)) {
					iterator.remove();
					deletedUser = userIdDb;
				}
			}
			rec.setUsers(userList);
			recommendationRepository.save(rec);
			if (deletedUser != null) {
				return true;
			} else {
				throw new UserNotFoundException("Haven't recommended book yet.");
			}

		} else {
			throw new BookNotFoundException("Book Not Found");
		}
	}

	@Override
	public Book addtoRecommendations(Book book, String userId) throws RecommendationAlreadyExists {
		// TODO Auto-generated method stub
		if (recommendationRepository.existsByBookId(book.getId())) {
			Recommendation rec = recommendationRepository.findByBookId(book.getId());
			List userList = rec.getUsers();
			Iterator iterator = userList.iterator();

			while (iterator.hasNext()) {
				String userIdDb = (String) iterator.next();
				if (userIdDb.equals(userId)) {
					throw new RecommendationAlreadyExists("Already recommended.");
				}
			}

			userList.add(userId);
			rec.setUsers(userList);
			recommendationRepository.save(rec);
			return book;
		} else {
			Recommendation rec = new Recommendation();
			rec.setBookId(book.getId());
			rec.setBook(book);
			List userList = new ArrayList<>();
			userList.add(userId);
			rec.setUsers(userList);
			recommendationRepository.insert(rec);
			return book;
		}
	}

}
