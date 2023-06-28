package com.aniket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aniket.model.Booking;

public interface BookingRepo extends MongoRepository<Booking, Integer> {
	
	Booking findAllByFirstName(String name);

}
