package com.stackroute.bookapp.FavouriteService.model;

import org.springframework.data.annotation.Id;

public class Book {

	@Id
	private String id;
	public Book(String id, String name, String booklink, String bookimagelink) {
		super();
		this.id = id;
		this.name = name;
		this.booklink = booklink;
		this.bookimagelink = bookimagelink;
	}
	private String name;
	private String booklink;
	private String bookimagelink;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBooklink() {
		return booklink;
	}
	public void setBooklink(String booklink) {
		this.booklink = booklink;
	}
	public String getBookimagelink() {
		return bookimagelink;
	}
	public void setBookimagelink(String bookimagelink) {
		this.bookimagelink = bookimagelink;
	}
	
}
