package com.pip.fitnessApplication.dto;

import java.util.Date;
import lombok.Data;


@Data
public class WorkoutDTO {

   
    private Long id;

    
    private String type;

    
    private Date date;

    
    private int duration;

    
    private int caloriesBurned;
    
   
    private Long userId;
}