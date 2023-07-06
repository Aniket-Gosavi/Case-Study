package com.aniket.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.aniket.repository.BookingRepo;
import com.aniket.repository.TrainRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import mailservice.EmailServiceImpl;

@Service
public class BookingServiceImpl implements BookingService{
	
	private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
	
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
			log.warn("booking not found by the given name "+name);
			throw new ResourceNotFoundException("not found");
		}
		return book;
	}


	@Override
	public Booking bookTrain(Booking book) {
		TrainDetails traindetails = trepo.findByTrainNo(book.getTrainNo());
		
		book.setId(seq.getSequenceNum(Booking.sequenceName));
		double fair = traindetails.getFair();
		book.setFair(fair * book.getNumberOfTravellers());
		Booking bk = brepo.save(book);
		traindetails.setTicketsAvailable(traindetails.getTicketsAvailable() - book.getNumberOfTravellers());
		trepo.save(traindetails);
		String body = "Hello "+book.getFirstName()+" "+book.getLastName()+" ,We have received your booking for ID:"+book.getId()+""
				+ "\n Boarding Station: "+traindetails.getBoardingStation()+""
				+ "\n Destination: "+traindetails.getDestination()+""
				+ "\n Train Name: "+traindetails.getName()+""+
				"\n Train Timing: "+traindetails.getTiming()+
				"\n Train Date: "+traindetails.getDate()+
				"\n Please Proceed to make payment for the Total Amount of Rs "+bk.getFair();
		esi.sendSimpleMail(book.getEmail(), body, "Booking Details");
		log.info("Booking successfully done for ID"+book.getId());
		return bk;
	}
	


	@Override
	public List<TrainDetails> showBySourceAndDestination(String source, String destination) throws ResourceNotFoundException{
		List<TrainDetails> show = trepo.findByBoardingStationAndDestination(source, destination);
		if(show.isEmpty()) {
			throw new ResourceNotFoundException("not found");
		}
		return show;
	}
	
	@Override
	public String onlinePayment(Booking book) throws RazorpayException {
		
		double amt= book.getFair();
		System.out.println(amt);
		
		 RazorpayClient client =  new RazorpayClient("rzp_test_BL4rFuV9nKRoMc","g91auwnxjWhSoguFayUZScuo" );
		 JSONObject options = new JSONObject();
		 options.put("amount", amt*100);
		 options.put("currency", "INR");
		 options.put("receipt", "txn_123456");
		 Order order = client.Orders.create(options);
		 System.out.println(order);
		 return order.toString();
	}


	@Override
	public Booking cancelTicket(int id) throws ResourceNotFoundException{
		Optional<Booking> deleteBooking = brepo.findById(id);
		if(deleteBooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking not fount by the given Id" +id);
		}
		Booking cancel = deleteBooking.get();
		int trainno = cancel.getTrainNo();
		TrainDetails td = trepo.findByTrainNo(trainno);
		td.setTicketsAvailable(td.getTicketsAvailable() + cancel.getNumberOfTravellers());
		trepo.save(td);
		brepo.delete(cancel);
		String body = "Hello "+cancel.getFirstName()+" "+cancel.getLastName()+" ,We have Cancelled your booking for ID:"+cancel.getId()+"";
		esi.sendSimpleMail(cancel.getEmail(), body, "Booking Details");
		return cancel;
	}


	@Override
	public List<TrainDetails> showByDate(LocalDate date) {
		return trepo.findByDate(date);
	}
}
