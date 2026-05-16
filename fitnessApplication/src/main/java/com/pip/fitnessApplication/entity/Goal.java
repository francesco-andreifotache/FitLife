package com.pip.fitnessApplication.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import com.pip.fitnessApplication.dto.GoalDto;

@Entity
@Data
public class Goal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Date startDate;

    private Date endDate;

    private boolean achieved;

    public GoalDto getGoalDto(){
        GoalDto goalDto = new GoalDto();

        goalDto.setId(id);
        goalDto.setDescription(description);
        goalDto.setStartDate(startDate);
        goalDto.setEndDate(endDate);
        goalDto.setAchieved(achieved);

        return goalDto;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}