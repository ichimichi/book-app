package com.stackroute.bookapp.UserService.controllertest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
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
import com.stackroute.bookapp.UserService.controller.UserController;
import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.repository.UserRepository;
import com.stackroute.bookapp.UserService.service.UserService;
import com.stackroute.bookapp.UserService.service.UserServiceImpl;

import io.jsonwebtoken.Claims;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers=UserController.class)
public class ControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	private User user;
	private List<String>list;
	
	@MockBean
	private UserRepository repository;
	
	@MockBean
	private UserServiceImpl service;
	@MockBean
	private Claims claims;
	@MockBean
	private HttpServletRequest http;
	
	@InjectMocks
	private UserController controller;
	@SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		list=new ArrayList<>();
		list.add("fiction");
		list.add("mythology");
		user=new User();
		user.setDob(new Date());
		user.setEmail("abc@gmail.com");
		user.setId("1");
		user.setName("abc");
		user.setPassword("abc@123");
		user.setInterests(list);
		
	}
	@Test
	public void createuserTest()throws Exception{
		Mockito.when(service.registerUser((User)any())).thenReturn(user);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
		
		
	}
	@Test
	public void updateuserTest()throws Exception{
		Mockito.when(service.updateUser((String)any(),(User)any())).thenReturn(user);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
		
		
	}
	
	@Test
	public void deleteuserTest()throws Exception{
		Mockito.when(service.registerUser((User)any())).thenReturn(user);
		Mockito.when(service.deleteUser((String)any())).thenReturn(true);
		
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
    			.queryParam("id", user.getId())
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
		
		
	}
	@Test
	public void getUserByIdTest()throws Exception{
		Mockito.when(service.getUserById((String)any())).thenReturn(user);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
		
		
	}
	@Test
	public void addToInterestTest()throws Exception{
		Mockito.when(service.addToInterests((String)any(),(String)any())).thenReturn(user);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/interest")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
    			.queryParam("name", "self-help")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
		
		
	}
	@Test
	public void getAllInterestTest()throws Exception{
		Mockito.when(service.getAllInterest((String)any())).thenReturn(list);
		String key="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYmMiLCJzdWIiOiJhYmMiLCJpYXQiOjE2MTcwODE1Nzh9.aKr51UGG5C8mV4-FD0XOocdt_l_c_Adlh31bGhO1XDY";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/interest")
				.header("Authorization", "Bearer "+key)
    			.requestAttr("claims", claims)
        		.contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
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
