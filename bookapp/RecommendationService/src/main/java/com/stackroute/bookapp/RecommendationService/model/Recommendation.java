package com.stackroute.bookapp.RecommendationService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recommendation {
	@Id
	private String id;
	private String userId;
	private List<Book> books;
	
	public Recommendation() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List getBooks() {
		return books;
	}

	public void setBooks(List books) {
		this.books = books;
	}
	
	
}
