package com.aniket.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aniket.controller.UserController;
import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.aniket.service.BookingService;

public class UserControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookTrain() {
        Booking booking = new Booking();
        when(bookingService.bookTrain(any(Booking.class))).thenReturn(booking);

        ResponseEntity<?> response = userController.bookTrain(booking);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(booking);
        verify(bookingService, times(1)).bookTrain(any(Booking.class));
    }

    @Test
    public void testShowTrain() throws ResourceNotFoundException {
        List<TrainDetails> trainDetailsList = new ArrayList<>();
        when(bookingService.showTrains()).thenReturn(trainDetailsList);

        ResponseEntity<?> response = userController.showTrain();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(trainDetailsList);
        verify(bookingService, times(1)).showTrains();
    }

    @Test
    public void testShowTrainThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(bookingService.showTrains()).thenThrow(new ResourceNotFoundException("No Data Available to show"));

        try {
            userController.showTrain();
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("No Data Available to show");
        }

        verify(bookingService, times(1)).showTrains();
    }

    @Test
    public void testFindById() throws ResourceNotFoundException {
        Booking booking = new Booking();
        when(bookingService.showBookingById(anyInt())).thenReturn(booking);

        ResponseEntity<Booking> response = userController.findById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(booking);
        verify(bookingService, times(1)).showBookingById(anyInt());
    }

//    @Test
//    public void testFindByName() throws ResourceNotFoundException {
//        Booking bookingList = new ArrayList<>();
//        TrainDetails booking = new TrainDetails();
//        bookingList.add(booking);
//        when(bookingService.showBookingByName(anyString())).thenReturn(bookingList);
//
//        ResponseEntity<?> response = userController.findByName("John");
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(bookingList);
//        verify(bookingService, times(1)).showBookingByName(anyString());
//    }

    @Test
    public void testFindBySourceAndDestination() throws ResourceNotFoundException {
        List<TrainDetails> bookingList = new ArrayList<>();
        TrainDetails booking = new TrainDetails();
        bookingList.add(booking);
        when(bookingService.showBySourceAndDestination(anyString(), anyString())).thenReturn(bookingList);

        ResponseEntity<?> response = userController.findBySourceAndDestination("Station A", "Station B");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(bookingList);
        verify(bookingService, times(1)).showBySourceAndDestination(anyString(), anyString());
    }

    @Test
    public void testCancelBooking() throws ResourceNotFoundException {
        Booking booking = new Booking();
        when(bookingService.cancelTicket(anyInt())).thenReturn(booking);

        ResponseEntity<?> response = userController.cancelBooking(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(booking);
        verify(bookingService, times(1)).cancelTicket(anyInt());
    }
}
