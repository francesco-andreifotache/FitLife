package com.pip.fitnessApplication.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;
import com.pip.fitnessApplication.dto.WorkoutDTO;

@Entity
@Data
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Date date;

    private int duration;

    private int caloriesBurned;

    // Metoda reparată! Acum nu se mai apelează pe ea însăși.
    public WorkoutDTO getWorkoutDTO() {
        WorkoutDTO workoutDTO = new WorkoutDTO();

        workoutDTO.setId(this.id);
        workoutDTO.setType(this.type);
        workoutDTO.setDate(this.date);
        workoutDTO.setDuration(this.duration);
        workoutDTO.setCaloriesBurned(this.caloriesBurned);

        return workoutDTO;
    }
}