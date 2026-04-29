package com.pip.fitnessApplication.services.workout;

import com.pip.fitnessApplication.dto.WorkoutDTO;

public interface WorkoutService {
    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);
}
