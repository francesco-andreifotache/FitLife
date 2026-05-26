package com.pip.fitnessApplication.services.stats;

import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.dto.ActivityDto;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActivityDtoTest {

    @Test
    public void testGettersAndSetters() {
        // 1. Arrange (Pregătim obiectul DTO și datele de test)
        ActivityDto dto = new ActivityDto();
        Long id = 100L;
        Date now = new Date();
        int steps = 8500;
        int distance = 6;
        int calories = 350;
        Long userId = 1L;

        // 2. Act (Folosim setterele generate de Lombok pentru a introduce datele)
        dto.setId(id);
        dto.setDate(now);
        dto.setSteps(steps);
        dto.setDistance(distance);
        dto.setCaloriesBurned(calories);
        dto.setUserId(userId);

        // 3. Assert (Verificăm prin gettere că datele salvate sunt exact cele introduse)
        assertEquals(id, dto.getId());
        assertEquals(now, dto.getDate());
        assertEquals(steps, dto.getSteps());
        assertEquals(distance, dto.getDistance());
        assertEquals(calories, dto.getCaloriesBurned());
        assertEquals(userId, dto.getUserId());
    }

    @Test
    public void testToStringAndEquals() {
        // Testăm că Lombok generează corect și metodele de structură (toString)
        ActivityDto dto = new ActivityDto();
        dto.setId(1L);
        dto.setSteps(5000);

        assertNotNull(dto.toString());
        assertEquals(true, dto.toString().contains("steps=5000"));
    }
}