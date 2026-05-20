package com.pip.fitnessApplication.services.workout;

import com.pip.fitnessApplication.dto.WorkoutDTO;
import java.util.List;

public interface WorkoutService {
    WorkoutDTO postWorkout(WorkoutDTO workoutDTO, Long userId);

    List<WorkoutDTO> getWorkouts(Long userId);

    void deleteWorkout(Long id);
}
