package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.entity.User;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActivityTest {

    @Test
    public void testGettersSettersAndRelationships() {
        // 1. Arrange
        Activity activity = new Activity();
        Long id = 42L;
        Date testDate = new Date();
        int steps = 12000;
        int distance = 9;
        int calories = 550;
        
        User mockUser = new User();
        mockUser.setId(7L);

        // 2. Act
        activity.setId(id);
        activity.setDate(testDate);
        activity.setSteps(steps);
        activity.setDistance(distance);
        activity.setCaloriesBurned(calories);
        activity.setUser(mockUser);

        // 3. Assert
        assertEquals(id, activity.getId());
        assertEquals(testDate, activity.getDate());
        assertEquals(steps, activity.getSteps());
        assertEquals(distance, activity.getDistance());
        assertEquals(calories, activity.getCaloriesBurned());
        assertNotNull(activity.getUser());
        assertEquals(7L, activity.getUser().getId());
    }

    @Test
    public void testGetActivityDto_MappingIsCorrect() {
        // 1. Arrange
        Activity activity = new Activity();
        activity.setId(10L);
        activity.setDate(new Date());
        activity.setSteps(6000);
        activity.setDistance(4);
        activity.setCaloriesBurned(250);

        // 2. Act
        ActivityDto resultDto = activity.getActivityDto();

        // 3. Assert (Verificăm că metoda a mapat corect datele din entitate în DTO)
        assertNotNull(resultDto, "DTO-ul rezultat nu ar trebui să fie null");
        assertEquals(activity.getId(), resultDto.getId());
        assertEquals(activity.getDate(), resultDto.getDate());
        assertEquals(activity.getSteps(), resultDto.getSteps());
        assertEquals(activity.getDistance(), resultDto.getDistance());
        assertEquals(activity.getCaloriesBurned(), resultDto.getCaloriesBurned());
    }
}