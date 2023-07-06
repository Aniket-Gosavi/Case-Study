//package com.aniket.testcases;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.aniket.controller.TrainController;
//import com.aniket.exception.ResourceNotFoundException;
//import com.aniket.model.TrainDetails;
//import com.aniket.service.TrainServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@WebMvcTest(TrainController.class)
//class TrainControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TrainServiceImpl trainService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testAddTrain() throws Exception {
//        TrainDetails train = new TrainDetails();
//        train.setId(1);
//        train.setName("Express Train");
//        train.setTrainNo(1234);
//        train.setBoardingStation("Station A");
//        train.setDestination("Station B");
//        train.setTiming("12:00 PM");
//        train.setDate("2023-06-10");
//        train.setFair(10.0);
//        train.setTicketsAvailable(100);
//
//        when(trainService.addTrain(any(TrainDetails.class))).thenReturn(train);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(train)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Express Train"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNo").value(1234))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingStation").value("Station A"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.destination").value("Station B"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.timingAndDate").value("2023-07-02 09:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fair").value(10.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketsAvailable").value(100));
//
//        verify(trainService, times(1)).addTrain(any(TrainDetails.class));
//    }
//
//    @Test
//    void testShowTrain() throws Exception {
//        TrainDetails train1 = new TrainDetails();
//        train1.setId(1);
//        train1.setName("Express Train");
//        train1.setTrainNo(1234);
//        train1.setBoardingStation("Station A");
//        train1.setDestination("Station B");
//        train1.setTimingAndDate("2023-07-02 09:00");
//        train1.setFair(10.0);
//        train1.setTicketsAvailable(100);
//
//        TrainDetails train2 = new TrainDetails();
//        train2.setId(2);
//        train2.setName("Local Train");
//        train2.setTrainNo(5678);
//        train2.setBoardingStation("Station C");
//        train2.setDestination("Station D");
//        train2.setTimingAndDate("2023-07-02 10:00");
//        train2.setFair(5.0);
//        train2.setTicketsAvailable(50);
//
//        when(trainService.showTrains()).thenReturn(List.of(train1, train2));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/show"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Express Train"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].trainNo").value(1234))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].boardingStation").value("Station A"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].destination").value("Station B"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].timingAndDate").value("2023-07-02 09:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fair").value(10.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticketsAvailable").value(100))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Local Train"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].trainNo").value(5678))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].boardingStation").value("Station C"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].destination").value("Station D"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].timingAndDate").value("2023-07-02 10:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fair").value(5.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].ticketsAvailable").value(50));
//
//        verify(trainService, times(1)).showTrains();
//    }
//
//    @Test
//    void testUpdateTrain() throws Exception {
//        int trainId = 1;
//        String destination = "Station C";
//
//        TrainDetails updatedTrain = new TrainDetails();
//        updatedTrain.setId(trainId);
//        updatedTrain.setName("Express Train");
//        updatedTrain.setTrainNo(1234);
//        updatedTrain.setBoardingStation("Station A");
//        updatedTrain.setDestination(destination);
//        updatedTrain.setTimingAndDate("2023-07-02 09:00");
//        updatedTrain.setFair(10.0);
//        updatedTrain.setTicketsAvailable(100);
//
//        when(trainService.updateTrain(eq(trainId), eq(destination))).thenReturn(updatedTrain);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}/{destination}", trainId, destination))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(trainId))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Express Train"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.trainNo").value(1234))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.boardingStation").value("Station A"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.destination").value(destination))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.timingAndDate").value("2023-07-02 09:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.fair").value(10.0))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketsAvailable").value(100));
//
//        verify(trainService, times(1)).updateTrain(eq(trainId), eq(destination));
//    }
//
//    @Test
//    void testDeleteTrain() throws Exception {
//        int trainId = 1;
//
//        when(trainService.deleteTrain(eq(trainId))).thenReturn("Deleted Successfully");
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", trainId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Deleted Successfully"));
//
//        verify(trainService, times(1)).deleteTrain(eq(trainId));
//    }
//
//}
