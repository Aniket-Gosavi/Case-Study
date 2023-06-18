package com.aniket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import mailservice.EmailServiceImpl;

@Service
public class BookingService implements UserServiceImpl{
	
	private static final Logger log = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	private BookingRepo brepo;
	
	@Autowired
	private TrainRepo trepo;

	@Autowired
	private EmailServiceImpl esi;

	@Autowired
	SequenceGeneratorService seq;
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
		Booking book = brepo.findAllByFirstName(name);
		if(book == null) {
			log.info("booking not found by the given name "+name);
			throw new ResourceNotFoundException("not found");
		}
		return book;
	}


	@Override
	public Booking bookTrain(Booking book) {
		TrainDetails td = trepo.findByName(book.getTrainName().toLowerCase());
		
		book.setId(seq.getSequenceNum(Booking.sequenceName));
		Map<String, Double> tt = getMap();
		String trainname = book.getTrainName();
		double fair = tt.get(trainname);
		book.setFair(fair * book.getNumberOfTravellers());
		Booking bk = brepo.save(book);
		String body = "Hello "+book.getFirstName()+" "+book.getLastName()+" ,We have received your booking for ID:"+book.getId()+""
				+ "\n Boarding Station: "+book.getSource()+""
				+ "\n Destination: "+book.getDestination()+""
				+ "\n Train Name: "+book.getTrainName()+""+
				"\n Train Timing And Date: "+td.getTimingAndDate()+
				"\n Please Proceed to make payment for the Total Amount of Rs "+bk.getFair();
		esi.sendSimpleMail(book.getEmail(), body, "Booking Details");
		log.info("Booking successfully done for ID"+book.getId());
		return bk;
	}
	
	public Map getMap() {
		Map<String, Double> trainTickets = new HashMap<>();
	    trainTickets.put("duranto", 400.00);
	    trainTickets.put("jhelam", 300.00);
	    trainTickets.put("rajdhani", 180.00);
	    trainTickets.put("vande bharat", 500.00);
	    trainTickets.put("shatabdi", 600.00);
	    return trainTickets;
	}


	@Override
	public List<TrainDetails> showBySourceAndDestination(String source, String destination) throws ResourceNotFoundException{
		List<TrainDetails> show = trepo.findByBoardingStationAndDestination(source, destination);
		if(show.isEmpty()) {
			throw new ResourceNotFoundException("not found");
		}
		return show;
	}
}
