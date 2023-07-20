package com.aniket.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "SaveBooking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedBookings {

	@Transient
	public static final String sequenceName = "BookingSequence";

	@Id
	@NotEmpty
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Email shoud not be empty")
	private String email;
	private int trainNo;
	@NotEmpty(message = "Travellers shoud not be empty")
	private int numberOfTravellers;
	private List<Passengers> passList; 

}
