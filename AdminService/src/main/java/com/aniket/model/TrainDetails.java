package com.aniket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "traindetails")
public class TrainDetails {

	@Id
	@NotNull
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String name;
	@NotEmpty(message = "TrainNo shoud not be empty")
	private int trainNo;
	private String boardingStation;
	private String destination;
	private String timingAndDate;
	private double fair;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getBoardingStation() {
		return boardingStation;
	}

	public void setBoardingStation(String boardingStation) {
		this.boardingStation = boardingStation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTimingAndDate() {
		return timingAndDate;
	}

	public void setTimingAndDate(String timingAndDate) {
		this.timingAndDate = timingAndDate;
	}

	public double getFair() {
		return fair;
	}

	public void setFair(double fair) {
		this.fair = fair;
	}

	public TrainDetails(@NotNull int id, String name, int trainNo, String boardingStation, String destination,
			String timingAndDate, double fair) {
		super();
		this.id = id;
		this.name = name;
		this.trainNo = trainNo;
		this.boardingStation = boardingStation;
		this.destination = destination;
		this.timingAndDate = timingAndDate;
		this.fair = fair;
	}

	public TrainDetails() {
		super();
	}

}
