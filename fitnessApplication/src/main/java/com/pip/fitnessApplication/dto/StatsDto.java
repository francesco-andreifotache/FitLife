package com.pip.fitnessApplication.dto;

import lombok.Data;

@Data
public class StatsDto {
    
    private Long achivedGaols;
    private Long notAchivedGoals;

    private int steps;
    private Double distance;
    private int totalCaloriesBurned;

    private int duration;

}
