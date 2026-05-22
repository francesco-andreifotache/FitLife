package com.pip.fitnessApplication.services.food;

import com.pip.fitnessApplication.dto.FoodDto;
import com.pip.fitnessApplication.entity.Food;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.FoodRepository;
import com.pip.fitnessApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    @Override
    public FoodDto postFood(FoodDto foodDto, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Food food = new Food();
            food.setName(foodDto.getName());
            food.setCalories(foodDto.getCalories());
            food.setProtein(foodDto.getProtein());
            food.setCarbs(foodDto.getCarbs());
            food.setFat(foodDto.getFat());
            food.setDate(foodDto.getDate());
            food.setUser(optionalUser.get());

            Food savedFood = foodRepository.save(food);
            return savedFood.getFoodDto();
        }
        return null;
    }

    @Override
    public List<FoodDto> getFoods(Long userId) {
        return foodRepository.findAllByUserId(userId).stream()
                .map(Food::getFoodDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}