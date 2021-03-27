package com.stackroute.bookapp.FavouriteService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	@Id
	private String userId;
	private List<Book>bookList;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Book> getBookList() {
		return bookList;
	}
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	public User(String userId, List<Book> bookList) {
		super();
		this.userId = userId;
		this.bookList = bookList;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
