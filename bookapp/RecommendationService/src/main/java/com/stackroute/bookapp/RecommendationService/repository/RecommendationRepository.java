package com.stackroute.bookapp.RecommendationService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.bookapp.RecommendationService.model.Recommendation;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String>{

}
