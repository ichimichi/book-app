package com.stackroute.bookapp.UserAuthenticationService.controller;

import java.util.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookapp.UserAuthenticationService.model.User;
import com.stackroute.bookapp.UserAuthenticationService.service.UserAuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAuthenticationControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAuthenticationService authenticationService;

    private User user;

    @InjectMocks
    private UserAuthenticationController authenticationController;


    @SuppressWarnings("deprecation")
	@Before(value = "")
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup (authenticationController).build();

        user = new User();
        user.setName("Mack");
        user.setDob((java.sql.Date) new Date());
        user.setEmail("mack@gmail.com");
        user.setPassword("123456");
    }

    @Test
    public void testRegisterUser() throws Exception {

    	Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                .content(jsonToString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testLoginUser() throws Exception {


        String email = "mack@gmail.com";
        String password = "123456";


        Mockito.when(authenticationService.saveUser(user)).thenReturn(true);
        Mockito.when(authenticationService.findByEmailAndPassword(email, password)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").content(jsonToString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
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
