package com.stackroute.bookapp.RecommendationService.servicetest;

import static org.mockito.ArgumentMatchers.any;
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

import com.stackroute.bookapp.RecommendationService.repository.RecommendationRepository;
import com.stackroute.bookapp.RecommendationService.service.RecommendationServiceImpl;
import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.model.ImageLinks;
import com.stackroute.bookapp.RecommendationService.model.IndustryIdentifier;
import com.stackroute.bookapp.RecommendationService.model.Recommendation;
import com.stackroute.bookapp.RecommendationService.model.VolumeInfo;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


	
	private Recommendation recommendation;
	@Mock
	private RecommendationRepository repository;
	@InjectMocks
	private RecommendationServiceImpl serviceImpl;
	private List<String> users=null;
	private Book book;
	private ImageLinks imageLinks;
	private IndustryIdentifier industryIdentifier;
	private VolumeInfo volumeInfo;
	Optional<Recommendation> options;
	private List<Book>booklist;
	private List<Recommendation>reclist;
	
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
		volumeInfo.setPageCount(250);
		volumeInfo.setAverageRating("8.5");
		volumeInfo.setRatingsCount(1000);
		volumeInfo.setDescription("Magical World");
		volumeInfo.setIndustryIdentifiers(Arrays.asList(industryIdentifier));
		volumeInfo.setCategories(Arrays.asList("Fiction"));
		book=new Book();
		book.setId("1");
		book.setKind("Series");
		book.setVolumeInfo(volumeInfo);
		
//		user=new User();
//		user.setBookList(Arrays.asList(book));
//		user.setUserId("1");
	    System.out.println("end");

        users = new ArrayList<String>();
        booklist=new ArrayList<Book>();
        booklist.add(book);
        users.add("rd");
        recommendation=new Recommendation();
        recommendation.setBook(book);
        recommendation.setBookId(book.getId());
        recommendation.setId("1");
        recommendation.setUsers(users);
        reclist=new ArrayList<>();
        reclist.add(recommendation);
       
        options = Optional.of(recommendation);
    }
	@Test
    public void createRecommendationTestSuccess() throws Exception {
		
		boolean success=true;
//		when(repository.insert((User)any())).thenReturn("hello");
//		doReturn(true).when(repository).insert((User)any());
		when(repository.findByBookId((String)any())).thenReturn(recommendation);
		Book newBook=serviceImpl.addtoRecommendations(book,"rd");
//		System.out.println(status);
//		System.out.println(user.getUserId());

        Assert.assertEquals(newBook, book);
        

    }
	@Test
	public void deleteBooksSuccess()throws Exception{
		
		when(repository.findByBookId((String)any())).thenReturn(recommendation);
		when(repository.existsByBookId((String)any())).thenReturn(true);
		
		boolean status=serviceImpl.removeBookByUser("1", "rd");
		Assert.assertEquals(true, status);
		
		
	}
//	
	@Test
	public void getAllBooksSuccess() throws Exception{
		
		when(repository.findAll()).thenReturn(reclist);
		when(repository.findById((String)any())).thenReturn(options);
		
		List<Recommendation>templist=serviceImpl.getAllRecommendedBooks("rd");
	    Assert.assertEquals(reclist, templist);
		
		
			
	}


}
