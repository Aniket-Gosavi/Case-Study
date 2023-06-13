package com.aniket.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traindetails")
public class TrainDetails {
	private int id;
	private String name;
	private int trainNo;
	private String boardingStation;
	private String destination;
	private String timingAndDate;

	public TrainDetails(int id, String name, int trainNo, String boardingStation, String destination,
			String timingAndDate) {
		super();
		this.id = id;
		this.name = name;
		this.trainNo = trainNo;
		this.boardingStation = boardingStation;
		this.destination = destination;
		this.timingAndDate = timingAndDate;
	}

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

	public TrainDetails() {
		super();
	}

}
