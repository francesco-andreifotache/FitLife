package com.pip.fitnessApplication.services.stats;

import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.dto.WorkoutDTO;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WorkoutDTOTest {

    @Test
    public void testGettersAndSetters() {
        
        WorkoutDTO dto = new WorkoutDTO();
        Long id = 55L;
        String type = "Strength Training";
        Date now = new Date();
        int duration = 60;
        int calories = 450;
        Long userId = 2L;

        
        dto.setId(id);
        dto.setType(type);
        dto.setDate(now);
        dto.setDuration(duration);
        dto.setCaloriesBurned(calories);
        dto.setUserId(userId);

        
        assertEquals(id, dto.getId());
        assertEquals(type, dto.getType());
        assertEquals(now, dto.getDate());
        assertEquals(duration, dto.getDuration());
        assertEquals(calories, dto.getCaloriesBurned());
        assertEquals(userId, dto.getUserId());
    }

    @Test
    public void testToStringAndEquals() {
        WorkoutDTO workout1 = new WorkoutDTO();
        workout1.setId(1L);
        workout1.setType("Cardio");

        WorkoutDTO workout2 = new WorkoutDTO();
        workout2.setId(1L);
        workout2.setType("Cardio");

        
        assertEquals(workout1, workout2, "Doua DTO-uri cu aceleasi valori interne trebuie sa fie egale.");
        assertNotNull(workout1.toString(), "Metoda toString() nu ar trebui sa returneze null.");
        assertEquals(true, workout1.toString().contains("Cardio"));
    }
}