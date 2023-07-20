package com.aniket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aniket.model.SavedBookings;

public interface SaveRepo extends MongoRepository<SavedBookings, Integer> {
	

}
