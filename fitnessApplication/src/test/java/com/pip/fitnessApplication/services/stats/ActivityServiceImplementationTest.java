package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.ActivityRepository;
import com.pip.fitnessApplication.repository.UserRepository;
import com.pip.fitnessApplication.services.activity.ActivityServiceImplementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceImplementationTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ActivityServiceImplementation activityService;

    @Test
    public void testPostActivity_Success() {
        // 1. Arrange
        Long userId = 1L;
        ActivityDto inputDto = new ActivityDto();
        inputDto.setDate(new Date());
        inputDto.setSteps(10000);
        inputDto.setDistance(7);
        inputDto.setCaloriesBurned(400);

        User mockUser = new User();
        mockUser.setId(userId);

        Activity mockActivity = new Activity();
        mockActivity.setId(50L);
        mockActivity.setSteps(inputDto.getSteps());

        // Simulăm comportamentul repository-urilor
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        Mockito.when(activityRepository.save(Mockito.any(Activity.class))).thenReturn(mockActivity);

        // 2. Act
        ActivityDto resultDto = activityService.postActivity(inputDto, userId);

        // 3. Assert
        assertNotNull(resultDto);
        assertEquals(50L, resultDto.getId());
        assertEquals(10000, resultDto.getSteps());
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(activityRepository, Mockito.times(1)).save(Mockito.any(Activity.class));
    }

    @Test
    public void testPostActivity_UserNotFound_ThrowsException() {
        // 1. Arrange
        Long userId = 99L;
        ActivityDto inputDto = new ActivityDto();

        // Simulăm cazul în care utilizatorul nu este găsit în baza de date
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // 2. Act & 3. Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            activityService.postActivity(inputDto, userId);
        });

        assertEquals("User not found", exception.getMessage());
        // Ne asigurăm că metoda save nu a mai fost apelată deloc deoarece a crăpat înainte
        Mockito.verify(activityRepository, Mockito.never()).save(Mockito.any(Activity.class));
    }

    @Test
    public void testGetActivities_ReturnsList() {
        // 1. Arrange
        Long userId = 1L;
        Activity act1 = new Activity();
        act1.setId(10L);
        Activity act2 = new Activity();
        act2.setId(20L);

        Mockito.when(activityRepository.findAllByUserId(userId)).thenReturn(List.of(act1, act2));

        // 2. Act
        List<ActivityDto> result = activityService.getActivities(userId);

        // 3. Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(10L, result.get(0).getId());
        assertEquals(20L, result.get(1).getId());
        Mockito.verify(activityRepository, Mockito.times(1)).findAllByUserId(userId);
    }

    @Test
    public void testDeleteActivity_Success() {
        // 1. Arrange
        Long activityId = 100L;
        Mockito.doNothing().when(activityRepository).deleteById(activityId);

        // 2. Act
        activityService.deleteActivity(activityId);

        // 3. Assert
        Mockito.verify(activityRepository, Mockito.times(1)).deleteById(activityId);
    }
}