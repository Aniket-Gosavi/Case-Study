package com.aniket.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Booking {
	
	@NotEmpty
	private int id;
	private String firstName;
	private String lastName;
	private String source;
	private String destination;
	private int numberOfTravellers;
}
