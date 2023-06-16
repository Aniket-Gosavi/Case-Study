package com.aniket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.service.BookingService;

@RestController
public class UserController {

	@Autowired
	private BookingService bk;

	@PostMapping("/book")
	public ResponseEntity<?> bookTrain(@RequestBody Booking book) {
		return ResponseEntity.ok(bk.bookTrain(book));
	}

	@GetMapping("/show")
	public ResponseEntity<?> showTrain() {
		return ResponseEntity.ok(bk.showTrains());
	}

	@GetMapping("/findbyId/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.showBookingById(id));
	}
	
	@GetMapping("/findbyname/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.showBookingByName(name));
	}

}
