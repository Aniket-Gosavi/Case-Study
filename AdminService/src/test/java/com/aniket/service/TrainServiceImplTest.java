package com.aniket.service;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.repository.TrainRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TrainServiceImplTest {

    @Mock
    private TrainRepo trepo;

    @InjectMocks
    private TrainServiceImpl trainService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTrain() {
        TrainDetails trainDetails = new TrainDetails();
        trainDetails.setId(1);
        trainDetails.setName("Jhelam");

        when(trepo.save(trainDetails)).thenReturn(trainDetails);

        TrainDetails savedTrain = trainService.addTrain(trainDetails);

        Assert.assertNotNull(savedTrain);
        Assert.assertEquals(trainDetails.getId(), savedTrain.getId());
        Assert.assertEquals(trainDetails.getName(), savedTrain.getName());

        verify(trepo, times(1)).save(trainDetails);
    }

    @Test
    public void testShowTrains() {
        List<TrainDetails> trainList = new ArrayList<>();
        TrainDetails train1 = new TrainDetails();
        train1.setId(1);
        train1.setName("Train 1");
        trainList.add(train1);

        when(trepo.findAll()).thenReturn(trainList);

        List<TrainDetails> retrievedTrains = trainService.showTrains();

        Assert.assertNotNull(retrievedTrains);
        Assert.assertEquals(trainList.size(), retrievedTrains.size());
        Assert.assertEquals(trainList.get(0).getId(), retrievedTrains.get(0).getId());
        Assert.assertEquals(trainList.get(0).getName(), retrievedTrains.get(0).getName());

        verify(trepo, times(1)).findAll();
    }

    @Test
    public void testUpdateTrain() throws ResourceNotFoundException {
        int trainId = 1;
        String newDestination = "New Destination";

        TrainDetails existingTrain = new TrainDetails();
        existingTrain.setId(trainId);
        existingTrain.setName("Train Name");

        when(trepo.findById(trainId)).thenReturn(Optional.of(existingTrain));
        when(trepo.save(existingTrain)).thenReturn(existingTrain);

        TrainDetails updatedTrain = trainService.updateTrain(trainId, newDestination);

        Assert.assertNotNull(updatedTrain);
        Assert.assertEquals(trainId, updatedTrain.getId());
        Assert.assertEquals(newDestination, updatedTrain.getDestination());

        verify(trepo, times(1)).findById(trainId);
        verify(trepo, times(1)).save(existingTrain);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateTrainNotFound() throws ResourceNotFoundException {
        int trainId = 1;
        String newDestination = "New Destination";

        when(trepo.findById(trainId)).thenReturn(Optional.empty());

        trainService.updateTrain(trainId, newDestination);
    }

    @Test
    public void testDeleteTrain() throws ResourceNotFoundException {
        int trainId = 1;

        TrainDetails existingTrain = new TrainDetails();
        existingTrain.setId(trainId);
        existingTrain.setName("Train Name");

        when(trepo.findById(trainId)).thenReturn(Optional.of(existingTrain));
        doNothing().when(trepo).delete(existingTrain);

        String result = trainService.deleteTrain(trainId);

        Assert.assertNotNull(result);
        Assert.assertEquals("Deleted Successfully", result);

        verify(trepo, times(1)).findById(trainId);
        verify(trepo, times(1)).delete(existingTrain);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteTrainNotFound() throws ResourceNotFoundException {
        int trainId = 1;

        when(trepo.findById(trainId)).thenReturn(Optional.empty());

        trainService.deleteTrain(trainId);
    }
}
