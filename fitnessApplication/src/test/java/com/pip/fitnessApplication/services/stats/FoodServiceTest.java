package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.FoodDto;
import com.pip.fitnessApplication.services.food.FoodService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private FoodService foodService;

    @Test
    public void testPostFood_Success() {
        Long userId = 1L;
        FoodDto inputDto = new FoodDto();
        inputDto.setName("Banana");

        FoodDto mockSavedDto = new FoodDto();
        mockSavedDto.setId(10L);
        mockSavedDto.setName("Banana");

        Mockito.when(foodService.postFood(inputDto, userId)).thenReturn(mockSavedDto);

        FoodDto result = foodService.postFood(inputDto, userId);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Banana", result.getName());
    }

    @Test
    public void testGetFoods_Success() {
        Long userId = 1L;
        List<FoodDto> mockList = List.of(new FoodDto(), new FoodDto());

        Mockito.when(foodService.getFoods(userId)).thenReturn(mockList);

        List<FoodDto> result = foodService.getFoods(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteFood_Success() {
        Long foodId = 5L;

        Mockito.doNothing().when(foodService).deleteFood(foodId);

        foodService.deleteFood(foodId);

        Mockito.verify(foodService, Mockito.times(1)).deleteFood(foodId);
    }
}