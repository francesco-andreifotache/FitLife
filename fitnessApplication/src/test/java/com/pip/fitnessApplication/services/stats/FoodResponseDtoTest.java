package com.pip.fitnessApplication.services.stats;

import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.dto.FoodResponseDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FoodResponseDtoTest {

    @Test
    public void testGettersAndSetters() {
       
        FoodResponseDto dto = new FoodResponseDto();
        String foodName = "Avocado";
        double calories = 160.5;
        double protein = 2.0;
        double carbs = 8.5;
        double fat = 14.7;

        
        dto.setName(foodName);
        dto.setCalories(calories);
        dto.setProtein(protein);
        dto.setCarbs(carbs);
        dto.setFat(fat);

       
        assertEquals(foodName, dto.getName());
        assertEquals(calories, dto.getCalories(), 0.001);
        assertEquals(protein, dto.getProtein(), 0.001);
        assertEquals(carbs, dto.getCarbs(), 0.001);
        assertEquals(fat, dto.getFat(), 0.001);
    }

    @Test
    public void testToStringAndEquals() {
        FoodResponseDto item1 = new FoodResponseDto();
        item1.setName("Salmon");
        item1.setCalories(208.0);

        FoodResponseDto item2 = new FoodResponseDto();
        item2.setName("Salmon");
        item2.setCalories(208.0);

        
        assertEquals(item1, item2, "DTO-urile cu aceleași valori structurale trebuie să fie egale.");
        assertNotNull(item1.toString());
        assertEquals(true, item1.toString().contains("Salmon"));
    }
}