package com.aniket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
public class Booking {

	@Id
	@NotEmpty
	private int id;
	private String firstName;
	private String lastName;
	private String trainName;
	private String source;
	private String destination;
	private int numberOfTravellers;
	private double fair;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getNumberOfTravellers() {
		return numberOfTravellers;
	}

	public void setNumberOfTravellers(int numberOfTravellers) {
		this.numberOfTravellers = numberOfTravellers;
	}

	public double getFair() {
		return fair;
	}

	public void setFair(double fair) {
		this.fair = fair;
	}

	public Booking(@NotEmpty int id, String firstName, String lastName, String trainName, String source,
			String destination, int numberOfTravellers, double fair) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.numberOfTravellers = numberOfTravellers;
		this.fair = fair;
	}

	public Booking() {
		super();
	}

}
