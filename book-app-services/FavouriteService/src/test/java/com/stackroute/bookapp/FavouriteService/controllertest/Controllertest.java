package com.stackroute.bookapp.FavouriteService.controllertest;


import static org.hamcrest.CoreMatchers.any;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookapp.FavouriteService.config.JWTFilter;
import com.stackroute.bookapp.FavouriteService.controller.FavouriteController;
import com.stackroute.bookapp.FavouriteService.model.Book;
import com.stackroute.bookapp.FavouriteService.model.ImageLinks;
import com.stackroute.bookapp.FavouriteService.model.IndustryIdentifier;
import com.stackroute.bookapp.FavouriteService.model.User;
import com.stackroute.bookapp.FavouriteService.model.VolumeInfo;
import com.stackroute.bookapp.FavouriteService.repository.FavouriteRepository;
import com.stackroute.bookapp.FavouriteService.service.FavouriteServiceImpl;

import io.jsonwebtoken.Claims;

@RunWith(SpringJUnit4ClassRunner.class)

@WebMvcTest(controllers=FavouriteController.class)
public class Controllertest {
	
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavouriteServiceImpl service;
    @MockBean
    private FavouriteRepository repository;
    
    @MockBean
    private Claims claims;

    
    private User user;
	private Book book;
	private ImageLinks imageLinks;
	private IndustryIdentifier industryIdentifier;
	private VolumeInfo volumeInfo;
	Optional<User> options,op2;

    @InjectMocks
    private FavouriteController controller;
    
    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup (controller).build();
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

        
    }

    @Test
    public void addBookTest() throws Exception{
    	Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
    	Mockito.when(service.addBook((Book)any(),(String)any())).thenReturn(true);
    	mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fav")
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(book)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void removeBookTest() throws Exception{
    	System.out.println("remove");
    	Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
    	Mockito.when(service.removeBook((String)any(),(String)any())).thenReturn(true);
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fav")
    			.requestAttr("claims",claims)
    			.queryParam("bookId", book.getId())
    			.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(book)))
        		.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    	
    }
    @Test
    public void deleteAll() throws Exception{
    	Mockito.when(claims.getId()).thenReturn("abcdefsa111ss");
    	Mockito.when(service.deleteAllBooks((String)any())).thenReturn(true);
    	mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/fav/all")
    		   .requestAttr("claims", claims)
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
