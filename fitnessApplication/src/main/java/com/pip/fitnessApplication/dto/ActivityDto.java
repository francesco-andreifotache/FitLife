package com.pip.fitnessApplication.dto;

import lombok.Data;
import java.util.Date;


@Data
public class ActivityDto {

    
    private Long id;

    
    private Date date;

    
    private int steps;

    
    private int distance;

    
    private int caloriesBurned;

    
    private Long userId;
}