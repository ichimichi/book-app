package com.stackroute.bookapp.RecommendationService.controllertest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookapp.RecommendationService.controller.RecommendationController;
import com.stackroute.bookapp.RecommendationService.jwtFilter.JwtFilter;
import com.stackroute.bookapp.RecommendationService.model.Book;
import com.stackroute.bookapp.RecommendationService.model.ImageLinks;
import com.stackroute.bookapp.RecommendationService.model.IndustryIdentifier;
import com.stackroute.bookapp.RecommendationService.model.Recommendation;
import com.stackroute.bookapp.RecommendationService.model.VolumeInfo;
import com.stackroute.bookapp.RecommendationService.repository.RecommendationRepository;
import com.stackroute.bookapp.RecommendationService.service.RecommendationServiceImpl;

import io.jsonwebtoken.Claims;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers=RecommendationController.class)
public class ControllerTest {

	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private RecommendationServiceImpl service;
	
	@MockBean
	private RecommendationRepository repository;
	
	@MockBean
	private Claims claims;
	@MockBean
	private HttpServletRequest http;
	@InjectMocks
	private JwtFilter filter;
	private List<String> users=null;
	private Recommendation recommendation;
	private Book book;
	private ImageLinks imageLinks;
	private IndustryIdentifier industryIdentifier;
	private VolumeInfo volumeInfo;
	Optional<Recommendation> options;
	private List<Book>booklist;
	private List<Recommendation>reclist;
	@InjectMocks
	private RecommendationController controller;
	@SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception{
		
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
	public void getAllBooksTest() throws Exception{
		
		Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
		Mockito.when(service.getAllRecommendedBooks((String)any())).thenReturn(reclist);
//		Mockito.when(http.getMethod()).thenReturn("OPTIONS");
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommendation")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void addRecommendationTest() throws Exception{
		Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
		Mockito.when(service.addtoRecommendations((Book)any(),(String)any())).thenReturn(book);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/recommendation")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	public void removeRecommendationTest() throws Exception{
		
		Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
		Mockito.when(service.removeBookByUser((String)any(), (String)any())).thenReturn(true);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/recommendation")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
    			.queryParam("bookId", book.getId())
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(book)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
	}
	
	
	 private static String jsonToString(final Object obj) throws JsonProcessingException {
	        String result;
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            final String jsonContent = mapper.writeValueAsString(obj);
	            result = jsonContent;
	        } catch (JsonProcessingException e) {
	            result = "Json processing error";
	        }
	        return result;
	    }
}
