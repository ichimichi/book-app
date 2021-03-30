package com.stackroute.bookapp.UserAuthenticationService.serviceTest;


import java.sql.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.stackroute.bookapp.UserAuthenticationService.exception.UserAlreadyExistException;
import com.stackroute.bookapp.UserAuthenticationService.model.User;
import com.stackroute.bookapp.UserAuthenticationService.repository.UserAuthenticationRepository;
import com.stackroute.bookapp.UserAuthenticationService.service.UserAuthenticationServiceImpl;





@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	@Mock
    private UserAuthenticationRepository autheticationRepository;

    private User user;
    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;
    
    Optional<User> optional;
    
    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        user = new User();
        user.setName("Mack");
        user.setDob(new Date(2020,8,12));
        user.setEmail("mack@gmail.com");
        user.setPassword("123456");
    }
    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistException {

        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
       Assert.assertEquals(true, flag);

    }


    @Test
    public void testSaveUserFailure() throws UserAlreadyExistException {

        Optional<User> optional=Optional.empty();
       
		Mockito.when(autheticationRepository.findByEmail("mack@gmail.com")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals( true, flag);
    }
}
