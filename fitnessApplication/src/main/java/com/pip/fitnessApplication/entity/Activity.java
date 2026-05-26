package com.pip.fitnessApplication.entity;

import com.pip.fitnessApplication.dto.ActivityDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.util.Date;


@Entity
@Data
public class Activity {

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private Date date;

   
    private int steps;

    private int distance;

    
    private int caloriesBurned;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
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
