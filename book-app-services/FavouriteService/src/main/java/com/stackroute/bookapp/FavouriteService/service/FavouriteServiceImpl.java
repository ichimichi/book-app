package com.stackroute.bookapp.FavouriteService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookapp.FavouriteService.exception.BookAlreadyExistsException;
import com.stackroute.bookapp.FavouriteService.exception.BookNotFoundException;
import com.stackroute.bookapp.FavouriteService.exception.UserNotFoundException;
import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.model.User;
import com.stackroute.bookapp.FavouriteService.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService{
	
	@Autowired
	FavouriteRepository repository;

	public FavouriteServiceImpl(FavouriteRepository repository) {
		super();
		this.repository = repository;
	}

	public FavouriteServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean addBook(Book book, String UserId) throws BookAlreadyExistsException {

		boolean status = false;
		User user = new User();
		List<Book> books = new ArrayList<>();
		if (repository.existsById(UserId)) {
			books = repository.findById(UserId).get().getBookList();
			Iterator iterator = books.iterator();
			Book temp = new Book();
			while (iterator.hasNext()) {

				temp = (Book) (iterator.next());
				if (temp.getId().equals(book.getId())) {
					throw new BookAlreadyExistsException("Book Already Exists");
				}
			}
			user.setUserId(UserId);
			books.add(book);
			user.setBookList(books);
			if (repository.save(user) != null) {
				status = true;
			}

		} else {

			user.setUserId(UserId);
			books.add(book);
			user.setBookList(books);
			if (repository.save(user) != null) {
				status = true;
			}
		}
		return status;
	}
	@Override
	public boolean removeBook(String bookId,String UserId)throws BookNotFoundException{
		
		boolean status = false;
		User user = new User();
		List<Book> books = new ArrayList<>();
		if (repository.existsById(UserId)) {
			books = repository.findById(UserId).get().getBookList();
			Iterator iterator = books.iterator();
			Book temp = new Book();
			boolean check=false;
			while (iterator.hasNext()) {

				temp = (Book) (iterator.next());
				if (temp.getId().equals(bookId)) {
					check=true;
					iterator.remove();
				}
			}
			if(!check) {
				throw new BookNotFoundException("Book not in the list");
			}
			user.setUserId(UserId);
			user.setBookList(books);
			repository.save(user);
			if(check)status=true;
		}
		else {
			
			throw new BookNotFoundException("User Doesnt exist");
		}
		return status;
		
	}
	@Override
	public boolean deleteAllBooks(String UserId)throws UserNotFoundException{
		boolean status = false;
		User user = new User();
		List<Book> books = new ArrayList<>();
		try {
			
		books = repository.findById(UserId).get().getBookList();
		System.out.println(books.size()+"hello");
		try {
		if(books.size()!=0) {
			
			books.clear();
			
			System.out.println("error also");
			
		}
		user.setUserId(UserId);
		user.setBookList(books);}
		catch(Exception e) {
			System.out.println("error here");
			throw new Exception();
		}
		repository.save(user);
		status=true;
		}
		catch(Exception e) {
			System.out.println("error");
			return false;
		}
		return status;
		
	}
	@Override
	public List<Book>getAllBooksByUserId(String userId)throws UserNotFoundException{
		
		List<Book> books = new ArrayList<>();
		if (repository.existsById(userId)) {
			
			books = repository.findById(userId).get().getBookList();
		}
		else {
			throw new UserNotFoundException("User Not Found!");
		}
		return books;
		
		
	}

	
}
