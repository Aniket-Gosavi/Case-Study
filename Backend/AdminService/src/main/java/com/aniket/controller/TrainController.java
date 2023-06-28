package com.aniket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.service.TrainServiceImpl;

@RestController
public class TrainController {
	
	@Autowired
	TrainServiceImpl ts;
	
	@PostMapping("/add")
	public ResponseEntity<?> addTrain(@RequestBody TrainDetails td){
		TrainDetails save = ts.addTrain(td);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("/show")
	public ResponseEntity<?> showTrain(){
		return ResponseEntity.ok(ts.showTrains());
	}
	
	@PutMapping("/update/{id}/{destination}")
	public ResponseEntity<?> updateTrain(@PathVariable int id, @PathVariable String destination) throws ResourceNotFoundException{
		return ResponseEntity.ok(ts.updateTrain(id, destination));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTrain(@PathVariable int id) throws ResourceNotFoundException{
		return ts.deleteTrain(id);
	}

}
