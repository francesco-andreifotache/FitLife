package com.pip.fitnessApplication.dto;

import lombok.Data;

import java.util.Date;

@Data // getters & setters (reduce boilerplate code)
public class ActivityDto {
    private Long id;

    private Date date;

    private int steps;

    private int distance;

    private int caloriesBurned;
}
