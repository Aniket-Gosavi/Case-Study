package com.aniket.service;

import java.util.List;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;

public interface UserServiceImpl {
	List<TrainDetails> showTrains();
	Booking bookTrain(Booking book);
	Booking showBookingById(int id)throws ResourceNotFoundException;
	Booking showBookingByName(String name) throws ResourceNotFoundException;
}
