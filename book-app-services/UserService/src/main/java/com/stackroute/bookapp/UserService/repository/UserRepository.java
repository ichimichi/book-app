package com.stackroute.bookapp.UserService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.bookapp.UserService.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
