package com.pip.fitnessApplication.services.stats;

import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.dto.ActivityDto;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActivityDtoTest {

    @Test
    public void testGettersAndSetters() {
        
        ActivityDto dto = new ActivityDto();
        Long id = 100L;
        Date now = new Date();
        int steps = 8500;
        int distance = 6;
        int calories = 350;
        Long userId = 1L;

        
        dto.setId(id);
        dto.setDate(now);
        dto.setSteps(steps);
        dto.setDistance(distance);
        dto.setCaloriesBurned(calories);
        dto.setUserId(userId);

        
        assertEquals(id, dto.getId());
        assertEquals(now, dto.getDate());
        assertEquals(steps, dto.getSteps());
        assertEquals(distance, dto.getDistance());
        assertEquals(calories, dto.getCaloriesBurned());
        assertEquals(userId, dto.getUserId());
    }

    @Test
    public void testToStringAndEquals() {
        
        ActivityDto dto = new ActivityDto();
        dto.setId(1L);
        dto.setSteps(5000);

        assertNotNull(dto.toString());
        assertEquals(true, dto.toString().contains("steps=5000"));
    }
}