package com.aniket.testcases;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.repository.TrainRepo;
import com.aniket.service.TrainServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainServiceImplTest {

    @Mock
    private TrainRepo trainRepo;

    @InjectMocks
    private TrainServiceImpl trainService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTrain() {
        // Arrange
        TrainDetails train = new TrainDetails();
        train.setId(1);
        train.setDestination("Destination");
        when(trainRepo.save(train)).thenReturn(train);

        // Act
        TrainDetails savedTrain = trainService.addTrain(train);

        // Assert
        assertNotNull(savedTrain);
        assertEquals(train.getId(), savedTrain.getId());
        assertEquals(train.getDestination(), savedTrain.getDestination());
        verify(trainRepo, times(1)).save(train);
    }

    @Test
    void testShowTrains() {
        // Arrange
        List<TrainDetails> trainList = new ArrayList<>();
        TrainDetails train1 = new TrainDetails();
        train1.setId(1);
        train1.setDestination("Destination 1");
        TrainDetails train2 = new TrainDetails();
        train2.setId(2);
        train2.setDestination("Destination 2");
        trainList.add(train1);
        trainList.add(train2);
        when(trainRepo.findAll()).thenReturn(trainList);

        // Act
        List<TrainDetails> result = trainService.showTrains();

        // Assert
        assertNotNull(result);
        assertEquals(trainList.size(), result.size());
        assertEquals(trainList, result);
        verify(trainRepo, times(1)).findAll();
    }

    @Test
    void testUpdateTrain() throws ResourceNotFoundException {
        // Arrange
        int trainId = 1;
        String newDestination = "New Destination";
        TrainDetails existingTrain = new TrainDetails();
        existingTrain.setId(trainId);
        existingTrain.setDestination("Old Destination");
        when(trainRepo.findById(trainId)).thenReturn(Optional.of(existingTrain));
        when(trainRepo.save(existingTrain)).thenReturn(existingTrain);

        // Act
        TrainDetails updatedTrain = trainService.updateTrain(trainId, newDestination);

        // Assert
        assertNotNull(updatedTrain);
        assertEquals(trainId, updatedTrain.getId());
        assertEquals(newDestination, updatedTrain.getDestination());
        verify(trainRepo, times(1)).findById(trainId);
        verify(trainRepo, times(1)).save(existingTrain);
    }

    @Test
    void testUpdateTrain_ThrowsResourceNotFoundException() {
        // Arrange
        int trainId = 1;
        String newDestination = "New Destination";
        when(trainRepo.findById(trainId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> trainService.updateTrain(trainId, newDestination));
        verify(trainRepo, times(1)).findById(trainId);
        verify(trainRepo, never()).save(any());
    }

    @Test
    void testDeleteTrain() throws ResourceNotFoundException {
        // Arrange
        int trainId = 1;
        TrainDetails existingTrain = new TrainDetails();
        existingTrain.setId(trainId);
        existingTrain.setDestination("Destination");
        when(trainRepo.findById(trainId)).thenReturn(Optional.of(existingTrain));

        // Act
        String result = trainService.deleteTrain(trainId);

        // Assert
        assertEquals("Deleted Successfully", result);
        verify(trainRepo, times(1)).findById(trainId);
        verify(trainRepo, times(1)).delete(existingTrain);
    }

    @Test
    void testDeleteTrain_ThrowsResourceNotFoundException() {
        // Arrange
        int trainId = 1;
        when(trainRepo.findById(trainId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> trainService.deleteTrain(trainId));
        verify(trainRepo, times(1)).findById(trainId);
        verify(trainRepo, never()).delete(any());
    }
}
