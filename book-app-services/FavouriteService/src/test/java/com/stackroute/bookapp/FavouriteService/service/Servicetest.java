package com.stackroute.bookapp.FavouriteService.service;






import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.model.ImageLinks;
import com.stackroute.bookapp.FavouriteService.model.IndustryIdentifier;
import com.stackroute.bookapp.FavouriteService.model.User;
import com.stackroute.bookapp.FavouriteService.model.VolumeInfo;
import com.stackroute.bookapp.FavouriteService.repository.FavouriteRepository;

@ExtendWith(MockitoExtension.class)
public class Servicetest {

	
	private  User user;
	@Mock
	private FavouriteRepository repository;
	@InjectMocks
	private FavouriteServiceImpl serviceImpl;
	private List<User> users=null;
	private Book book;
	private ImageLinks imageLinks;
	private IndustryIdentifier industryIdentifier;
	private VolumeInfo volumeInfo;
	Optional<User> options;
	private List<Book>booklist;
	@Before 
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        System.out.println("start");
		
		
        imageLinks=new ImageLinks();
        imageLinks.setSmallThumbnail("small image");
        imageLinks.setThumbnail("image");
        
        industryIdentifier=new IndustryIdentifier();
        industryIdentifier.setType("Publishing");
        industryIdentifier.setIdentifier("abcde");
        
		volumeInfo=new VolumeInfo();
		volumeInfo.setTitle("Harry Potter");
		volumeInfo.setAuthors(Arrays.asList("J.K Rowling"));
		volumeInfo.setPublisher("Penguin");
		volumeInfo.setPublishedDate(new Date());
		volumeInfo.setLanguage("English");
		volumeInfo.setPreviewLink("http://preview.com");
		volumeInfo.setImageLinks(imageLinks);
		volumeInfo.setPageCount("250");
		volumeInfo.setAverageRating("8.5");
		volumeInfo.setRatingsCount(1000);
		volumeInfo.setDescription("Magical World");
		volumeInfo.setIndustryIdentifiers(Arrays.asList(industryIdentifier));
		volumeInfo.setCategories(Arrays.asList("Fiction"));
		book=new Book();
		book.setId("1");
		book.setKind("Series");
		book.setVolumeInfo(volumeInfo);
		user=new User();
		user.setBookList(Arrays.asList(book));
		user.setUserId("1");
	    System.out.println("end");

        users = new ArrayList<User>();
        booklist=new ArrayList<Book>();
        booklist.add(book);
        users.add(user);
        options = Optional.of(user);
    }
	@Test
    public void createCategoryTestSuccess() throws Exception {
		
		boolean success=true;
//		when(repository.insert((User)any())).thenReturn("hello");
//		doReturn(true).when(repository).insert((User)any());
		when(repository.save((User)any())).thenReturn(user);
		boolean status=serviceImpl.addBook(book,user.getUserId());
		System.out.println(status);
		System.out.println(user.getUserId());

        Assert.assertEquals(success, status);
        

    }
	@Test
	public void deleteBooksSuccess()throws Exception{
		
		when(repository.findById((String)any())).thenReturn(options);
		when(repository.save((User)any())).thenReturn(user);
		boolean status=serviceImpl.deleteAllBooks(user.getUserId());
		Assert.assertEquals(false, status);
		
		
	}
	
	@Test
	public void getAllBooksSuccess() throws Exception{
		
		when(repository.existsById((String)any())).thenReturn(true);
		when(repository.findById((String)any())).thenReturn(options);
		
		List<Book>templist=serviceImpl.getAllBooksByUserId(user.getUserId());
		 Assert.assertEquals(booklist, templist);
		
		
		
		
		
		
		
	}



	
}
