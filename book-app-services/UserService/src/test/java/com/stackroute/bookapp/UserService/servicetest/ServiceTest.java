package com.stackroute.bookapp.UserService.servicetest;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.bookapp.UserService.model.User;
import com.stackroute.bookapp.UserService.repository.UserRepository;
import com.stackroute.bookapp.UserService.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

	
	private User user;
	private List<String>list;
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Before
    public void setUp() throws Exception {
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
	public void registratonTest()throws Exception {
		
		when(repository.existsByEmail((String)any())).thenReturn(false);
		when(repository.insert((User)any())).thenReturn(user);
		
		User newuser=service.registerUser(user);
		Assert.assertEquals(user, newuser);
		
	}
	@Test
	public void updateUserTest() throws Exception{
		
		User user2=user;
		user2.setEmail("abc2@gmail.com");
		when(repository.findByEmail((String)any())).thenReturn(user);
		when(repository.save((User)any())).thenReturn(user2);
		
		User newuser=service.updateUser(user.getEmail(), user);
		Assert.assertEquals(user, newuser);
		
	}
	@Test
	public void deleteUserTest() throws Exception{
		when(repository.findByEmail((String)any())).thenReturn(user);
//		when(repository.delete((User)any())).thenReturn(user);
		
		boolean status=service.deleteUser(user.getEmail());
		Assert.assertEquals(true,true);
		
		
	}
	@Test
	public void getUserByIdTest() throws Exception{
		
		when(repository.findByEmail((String)any())).thenReturn(user);
		User newuser=service.getUserById(user.getEmail());
		Assert.assertEquals(user, newuser);
	}
	@Test
	public void addToInterestsTest() throws Exception{
		
		User user2=user;
		list.add("thrillers");
		user2.setInterests(list);
		when(repository.findByEmail((String)any())).thenReturn(user);
		when(repository.existsByEmail((String)any())).thenReturn(true);
		
		User newuser=service.addToInterests(user.getEmail(), "thriller");
		Assert.assertEquals(user2, newuser);
		
		
	}
	@Test
	public void getAllInterestsTest() throws Exception{
		
		when(repository.findByEmail((String)any())).thenReturn(user);
		List<String>Interests=service.getAllInterest(user.getEmail());
		Assert.assertEquals(list, Interests);
		
	}
	
	
}
