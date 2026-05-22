package com.pip.fitnessApplication.dto;

import lombok.Data;
import java.util.Date;

@Data
public class FoodDto {
    private Long id;
    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private Date date;
    private Long userId;
}