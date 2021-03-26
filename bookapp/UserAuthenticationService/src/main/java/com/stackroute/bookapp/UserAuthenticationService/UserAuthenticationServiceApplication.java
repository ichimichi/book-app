package com.stackroute.bookapp.UserAuthenticationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAuthenticationServiceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(UserAuthenticationServiceApplication.class, args);
		System.out.println("hello from http://localhost:8084");
	}

}
