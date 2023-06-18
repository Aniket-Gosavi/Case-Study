package com.aniket.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.repository.TrainRepo;

@Service
public class TrainService implements TrainServiceImpl{
	
	private static final Logger log = LoggerFactory.getLogger(TrainService.class);
	
	@Autowired
	private TrainRepo trepo;

	@Override
	public TrainDetails addTrain(TrainDetails td) {
		TrainDetails save = trepo.save(td);
		return save;
	}

	@Override
	public List<TrainDetails> showTrains() {
		return trepo.findAll();
	}

	@Override
	public TrainDetails updateTrain(int id, String destination) throws ResourceNotFoundException {
		Optional<TrainDetails> td = trepo.findById(id);
		if(td.isEmpty()) {
			log.warn("Train with the specified Id is not avvailable" +id);
			throw new ResourceNotFoundException("Train with the following Id Does not Exist " +id);
		}
		TrainDetails tds = td.get();
		tds.setDestination(destination);
		TrainDetails save = trepo.save(tds);
		return save;
	}

	@Override
	public String deleteTrain(int id) throws ResourceNotFoundException {
		Optional<TrainDetails> td = trepo.findById(id);
		if(td.isEmpty()) {
			throw new ResourceNotFoundException("Train with the following Id Does not Exist " +id);
		}
		TrainDetails tds = td.get();
		trepo.delete(tds);
		return "Deleted Successfully";
	}

}