package com.aniket.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.aniket.repository.BookingRepo;
import com.aniket.repository.TrainRepo;

@Service
public class BookingService implements UserServiceImpl{
	
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private BookingRepo brepo;
	
	@Autowired
	private TrainRepo trepo;


	@Override
	public List<TrainDetails> showTrains() {
		return trepo.findAll();
	}


	@Override
	public Booking showBookingById(int id) throws ResourceNotFoundException {
		Optional<Booking> book = brepo.findById(id);
		if(book.isEmpty()) {
			log.info("booking not found by the given ID"+id);
			throw new ResourceNotFoundException("not found");
		}
		Booking bk = book.get();
		return bk;
	}

	@Override
	public Booking showBookingByName(String name) throws ResourceNotFoundException {
		Booking book = brepo.findAllByName(name);
		if(book == null) {
			log.info("booking not found by the given name "+name);
			throw new ResourceNotFoundException("not found");
		}
		return book;
	}


	@Override
	public Booking bookTrain(Booking book) {
		Booking bk = brepo.save(book); 
		return bk;
	}

}
