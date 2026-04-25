package com.pip.fitnessApplication.entity;

import com.pip.fitnessApplication.dto.ActivityDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity // Activity => tabel in MySQL
@Data
public class Activity {
    @Id // id => primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private int steps;

    private int distance;

    private int caloriesBurned;

    public ActivityDto getActivityDto(){
        ActivityDto activityDto = new ActivityDto();

        activityDto.setId(id);
        activityDto.setDate(date);
        activityDto.setSteps(steps);
        activityDto.setDistance(distance);
        activityDto.setCaloriesBurned(caloriesBurned);

        return activityDto;
    }

}
