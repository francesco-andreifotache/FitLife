package com.pip.fitnessApplication.services.food;

import com.pip.fitnessApplication.dto.FoodDto;
import java.util.List;

public interface FoodService {
    FoodDto postFood(FoodDto foodDto, Long userId);

    List<FoodDto> getFoods(Long userId);

    void deleteFood(Long id);
}