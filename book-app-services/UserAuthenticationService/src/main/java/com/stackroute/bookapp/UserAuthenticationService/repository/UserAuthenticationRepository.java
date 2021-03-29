package com.stackroute.bookapp.UserAuthenticationService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.bookapp.UserAuthenticationService.model.User;

public interface UserAuthenticationRepository extends JpaRepository<User, Integer> 
{
	User findByEmailAndPassword(String email, String password);
	Optional<User> findByEmail(String email);
	User findByIdAndPassword(int id, String password);
}

