package com.stackroute.bookapp.UserAuthenticationService;

import java.util.Date;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.stackroute.bookapp.UserAuthenticationService.exception.UserAlreadyExistException;
import com.stackroute.bookapp.UserAuthenticationService.exception.UserNotFoundException;
import com.stackroute.bookapp.UserAuthenticationService.model.User;
import com.stackroute.bookapp.UserAuthenticationService.repository.UserAuthenticationRepository;
import com.stackroute.bookapp.UserAuthenticationService.service.UserAuthenticationServiceImpl;


@SpringBootTest
class UserAuthenticationServiceApplicationTests {
	@Mock
    private UserAuthenticationRepository autheticationRepository;

    private User user;
    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;
    
    Optional<User> optional;
    
    @SuppressWarnings("deprecation")
	@Before(value = "")
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        user = new User();
        user.setName("Mack");
        user.setDob((java.sql.Date) new Date());
        user.setEmail("mack@gmail.com");
        user.setPassword("123456");
    }
    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistException {

        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);

    }


    @Test
    public void testSaveUserFailure() throws UserAlreadyExistException {

        java.util.Optional<User> optional;
		Mockito.when(autheticationRepository.findByEmail("mack@gmail.com")).thenReturn(optional);
        Mockito.when(autheticationRepository.save(user)).thenReturn(user);
        boolean flag = authenticationService.saveUser(user);
        Assert.assertEquals("Cannot Register User", true, flag);
    }
}
