package com.aniket.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aniket.model.TrainDetails;

@Repository
public interface TrainRepo extends MongoRepository<TrainDetails, Integer>{
	
	TrainDetails findByTrainNo(int trainNo);
	List<TrainDetails> findByBoardingStationAndDestination(String boardingStation,String destination);
}
