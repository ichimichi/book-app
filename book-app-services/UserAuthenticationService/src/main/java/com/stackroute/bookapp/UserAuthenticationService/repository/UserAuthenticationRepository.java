package com.stackroute.bookapp.UserAuthenticationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.bookapp.UserAuthenticationService.model.User;

public interface UserAuthenticationRepository extends JpaRepository<User, String> 
{
	User findByUserIdAndUserPassword(String userId, String userPassword);
}

