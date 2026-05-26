package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.repository.ActivityRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ActivityRepositoryTest {

    @Mock
    private ActivityRepository activityRepository;

    @Test
    public void testFindAllByUserId() {
        Long userId = 1L;
        List<Activity> mockActivities = List.of(new Activity(), new Activity());

        Mockito.when(activityRepository.findAllByUserId(userId)).thenReturn(mockActivities);

        List<Activity> result = activityRepository.findAllByUserId(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetTotalSteps() {
        Long userId = 1L;
        Integer expectedSteps = 25000;

        Mockito.when(activityRepository.getTotalSteps(userId)).thenReturn(expectedSteps);

        Integer result = activityRepository.getTotalSteps(userId);

        assertEquals(expectedSteps, result);
    }

    @Test
    public void testGetTotalDistance() {
        Long userId = 1L;
        Double expectedDistance = 18.5;

        Mockito.when(activityRepository.getTotalDistance(userId)).thenReturn(expectedDistance);

        Double result = activityRepository.getTotalDistance(userId);

        assertEquals(expectedDistance, result);
    }

    @Test
    public void testGetTotalCaloriesBurned() {
        Long userId = 1L;
        Integer expectedCalories = 1200;

        Mockito.when(activityRepository.getTotalCaloriesBurned(userId)).thenReturn(expectedCalories);

        Integer result = activityRepository.getTotalCaloriesBurned(userId);

        assertEquals(expectedCalories, result);
    }

    @Test
    public void testFindLast7Activities() {
        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 7);
        List<Activity> mockActivities = List.of(new Activity(), new Activity());

        Mockito.when(activityRepository.findLast7Activities(userId, pageable)).thenReturn(mockActivities);

        List<Activity> result = activityRepository.findLast7Activities(userId, pageable);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}