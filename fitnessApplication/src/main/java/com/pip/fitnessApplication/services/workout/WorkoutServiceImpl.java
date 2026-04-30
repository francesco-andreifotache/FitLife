package com.pip.fitnessApplication.services.workout;


import org.springframework.stereotype.Service;

import com.pip.fitnessApplication.dto.WorkoutDTO;
import com.pip.fitnessApplication.entity.Workout;
import com.pip.fitnessApplication.repository.WorkoutRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    
    private final WorkoutRepository workoutRepository;

    public WorkoutDTO postWorkout(WorkoutDTO workoutDTO) {
        Workout workout = new Workout();

        workout.setDate(workoutDTO.getDate());
        workout.setType(workoutDTO.getType());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());

        return workoutRepository.save(workout).getWorkoutDTO();

    }

    public List<WorkoutDTO> getWorkouts() {
        List<Workout> workouts = workoutRepository.findAll();

        return workouts.stream()
                .map(Workout::getWorkoutDTO)
                .collect(Collectors.toList());
    }
}
