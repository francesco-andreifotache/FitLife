package com.pip.fitnessApplication.dto;

import java.util.List;
import lombok.Data;

@Data
public class GraphDto {
    
    private List<WorkoutDTO> workouts;
    private List<ActivityDto> activities;
}
