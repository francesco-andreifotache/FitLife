package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.StatsDto;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.repository.ActivityRepository; 
import com.pip.fitnessApplication.repository.WorkoutRepository; 

import com.pip.fitnessApplication.services.Stats.StatsServiceImplementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsServiceImplTest {

    
    @Mock
    private GoalRepository goalRepository;

    
    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    
    @InjectMocks
    private StatsServiceImplementation statsService;

    @Test
    public void testGetStats_ReturnsCorrectData() {
        
        Long userId = 1L;
        Long expectedAchieved = 10L;
        Long expectedNotAchieved = 5L;

        
        when(goalRepository.countAchievedGoals(userId)).thenReturn(expectedAchieved);
        when(goalRepository.countNotAchievedGoals(userId)).thenReturn(expectedNotAchieved);
        
        
        when(activityRepository.getTotalSteps(userId)).thenReturn(10000);
        when(activityRepository.getTotalDistance(userId)).thenReturn(7.5);
        when(activityRepository.getTotalCaloriesBurned(userId)).thenReturn(400);
        when(workoutRepository.getTotalDuration(userId)).thenReturn(60);
        when(workoutRepository.getTotalCaloriesBurned(userId)).thenReturn(300);

        
        StatsDto result = statsService.getStats(userId);

        
        assertNotNull(result, "Rezultatul nu ar trebui să fie null");
        
        
        assertEquals(expectedAchieved, result.getAchivedGaols());
        assertEquals(expectedNotAchieved, result.getNotAchivedGoals());
        assertEquals(10000, result.getSteps());
        assertEquals(7.5, result.getDistance());
        assertEquals(700, result.getTotalCaloriesBurned()); 
        assertEquals(60, result.getDuration());

        
        verify(goalRepository).countAchievedGoals(userId);
        verify(goalRepository).countNotAchievedGoals(userId);
    }
}