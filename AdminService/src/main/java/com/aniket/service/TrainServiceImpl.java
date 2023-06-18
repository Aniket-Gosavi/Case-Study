package com.aniket.service;

import java.util.List;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;

public interface TrainServiceImpl {
	TrainDetails addTrain(TrainDetails td);
	List<TrainDetails> showTrains();
	TrainDetails updateTrain(int trainNo, String destination) throws ResourceNotFoundException;
	String deleteTrain(int id) throws ResourceNotFoundException;
}