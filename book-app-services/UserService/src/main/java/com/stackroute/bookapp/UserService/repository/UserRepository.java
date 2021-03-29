package com.stackroute.bookapp.UserService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.bookapp.UserService.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	boolean existsByEmail(String email);
	User findByEmail(String email);

}
