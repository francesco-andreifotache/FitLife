package com.pip.fitnessApplication.dto;

import lombok.Data;

@Data
public class FoodResponseDto {
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
}