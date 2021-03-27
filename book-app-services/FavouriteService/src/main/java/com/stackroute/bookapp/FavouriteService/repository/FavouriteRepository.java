package com.stackroute.bookapp.FavouriteService.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.bookapp.FavouriteService.model.User;

@Repository
public interface FavouriteRepository extends MongoRepository<User, String>{

}
