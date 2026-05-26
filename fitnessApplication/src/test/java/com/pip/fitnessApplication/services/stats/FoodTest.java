package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.FoodDto;
import com.pip.fitnessApplication.entity.Food;
import com.pip.fitnessApplication.entity.User;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {

    @Test
    public void testGettersAndSetters() {
        Food food = new Food();
        User user = new User();
        Date testDate = new Date();

        food.setId(10L);
        food.setName("Orez");
        food.setCalories(130.0);
        food.setProtein(2.7);
        food.setCarbs(28.0);
        food.setFat(0.3);
        food.setDate(testDate);
        food.setUser(user);

        assertEquals(10L, food.getId());
        assertEquals("Orez", food.getName());
        assertEquals(130.0, food.getCalories(), 0.001);
        assertEquals(2.7, food.getProtein(), 0.001);
        assertEquals(28.0, food.getCarbs(), 0.001);
        assertEquals(0.3, food.getFat(), 0.001);
        assertEquals(testDate, food.getDate());
        assertEquals(user, food.getUser());
    }

    @Test
    public void testGetFoodDto_ShouldMapCorrectly() {
        Food food = new Food();
        Date testDate = new Date();

        food.setId(5L);
        food.setName("Piept de pui");
        food.setCalories(165.0);
        food.setProtein(31.0);
        food.setCarbs(0.0);
        food.setFat(3.6);
        food.setDate(testDate);

        FoodDto dto = food.getFoodDto();

        assertNotNull(dto);
        assertEquals(food.getId(), dto.getId());
        assertEquals(food.getName(), dto.getName());
        assertEquals(food.getCalories(), dto.getCalories(), 0.001);
        assertEquals(food.getProtein(), dto.getProtein(), 0.001);
        assertEquals(food.getCarbs(), dto.getCarbs(), 0.001);
        assertEquals(food.getFat(), dto.getFat(), 0.001);
        assertEquals(food.getDate(), dto.getDate());
    }
}