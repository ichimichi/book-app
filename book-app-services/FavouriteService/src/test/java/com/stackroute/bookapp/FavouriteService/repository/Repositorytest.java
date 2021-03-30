package com.stackroute.bookapp.FavouriteService.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.model.ImageLinks;
import com.stackroute.bookapp.FavouriteService.model.IndustryIdentifier;
import com.stackroute.bookapp.FavouriteService.model.User;
import com.stackroute.bookapp.FavouriteService.model.VolumeInfo;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class Repositorytest {

	@Mock
	private FavouriteRepository repository;
	private User user;
	private Book book;
	private ImageLinks imageLinks;
	private IndustryIdentifier industryIdentifier;
	private VolumeInfo volumeInfo;
	Optional<User> options,op2;
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
		options = Optional.of(user);
		user.setUserId("2");
		op2=Optional.of(user);
		user.setUserId("1");
	    System.out.println("end");
		
		
    }
	
	
// @After 
//   public void tearDown() throws Exception {
//
//       repository.deleteAll();
//   }



	
	
	@Test
    public void createUserTest() {
		
		if(user==null)System.out.println("null user");
		else System.out.println("not null");
//        repository.insert(user);
//        Category fetchedCategory = categoryRepository.findById(category.getId()).get();
//        Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchedCategory.getId());
        when(repository.findById((String)any())).thenReturn(options);
        User newuser=repository.findById(user.getUserId()).get();
        Assert.assertEquals("1", user.getUserId());
        
    }
	@Test
	public void removeUserTest() {
		
//		repository.insert(user);
		 when(repository.findById((String)any())).thenReturn(options);
		User newuser=repository.findById(user.getUserId()).get();
		Assert.assertEquals("1", newuser.getUserId());
		
		repository.delete(newuser);
		when(repository.existsById((String)any())).thenReturn(false);
		boolean status=repository.existsById(user.getUserId());
		System.out.println(newuser.getUserId());
		Assert.assertEquals(false,status);
	}
	@Test
	public void updateUserTest() {
//		repository.insert(user);
		 when(repository.findById((String)any())).thenReturn(options);
		User newuser=repository.findById(user.getUserId()).get();
		Assert.assertEquals("1", newuser.getUserId());
		newuser.setUserId("2");
		repository.save(newuser);
		when(repository.findById((String)any())).thenReturn(op2);
		newuser=repository.findById(newuser.getUserId()).get();
		Assert.assertEquals("2", newuser.getUserId());
		
		
		
		
	}


}
