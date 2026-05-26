package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.StatsDto;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.repository.ActivityRepository; // Import adăugat
import com.pip.fitnessApplication.repository.WorkoutRepository;  // Import adăugat

import com.pip.fitnessApplication.services.Stats.StatsServiceImplementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals; // Adăugat pentru verificări
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatsServiceImplTest {

    // @Mock creează un obiect fals pentru dependența noastră
    @Mock
    private GoalRepository goalRepository;

    // Adăugăm mock-urile pentru a preveni NullPointerException în getStats
    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    // @InjectMocks injectează obiectele de tip mock în serviciul nostru
    @InjectMocks
    private StatsServiceImplementation statsService;

    @Test
    public void testGetStats_ReturnsCorrectData() {
        // 1. Arrange (Pregătirea datelor)
        Long userId = 1L;
        Long expectedAchieved = 10L;
        Long expectedNotAchieved = 5L;

        // Când se apelează metodele din repository, returnăm valorile noastre predefinite
        when(goalRepository.countAchievedGoals(userId)).thenReturn(expectedAchieved);
        when(goalRepository.countNotAchievedGoals(userId)).thenReturn(expectedNotAchieved);
        
        // Simulăm comportamentul noilor repository-uri apelate în metodă
        when(activityRepository.getTotalSteps(userId)).thenReturn(10000);
        when(activityRepository.getTotalDistance(userId)).thenReturn(7.5);
        when(activityRepository.getTotalCaloriesBurned(userId)).thenReturn(400);
        when(workoutRepository.getTotalDuration(userId)).thenReturn(60);
        when(workoutRepository.getTotalCaloriesBurned(userId)).thenReturn(300);

        // 2. Act (Apelarea metodei reale)
        StatsDto result = statsService.getStats(userId);

        // 3. Assert (Verificarea rezultatului)
        assertNotNull(result, "Rezultatul nu ar trebui să fie null");
        
        // Verificăm câmpurile din StatsDto folosind denumirile tale exacte din clasă:
        assertEquals(expectedAchieved, result.getAchivedGaols());
        assertEquals(expectedNotAchieved, result.getNotAchivedGoals());
        assertEquals(10000, result.getSteps());
        assertEquals(7.5, result.getDistance());
        assertEquals(700, result.getTotalCaloriesBurned()); // 400 + 300 calotii
        assertEquals(60, result.getDuration());

        // Verificăm că metodele din repository chiar au fost apelate
        verify(goalRepository).countAchievedGoals(userId);
        verify(goalRepository).countNotAchievedGoals(userId);
    }
}