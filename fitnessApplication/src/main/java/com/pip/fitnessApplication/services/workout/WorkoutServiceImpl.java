package com.pip.fitnessApplication.services.workout;


import org.springframework.stereotype.Service;

import com.pip.fitnessApplication.dto.WorkoutDTO;
import com.pip.fitnessApplication.entity.Workout;
import com.pip.fitnessApplication.repository.WorkoutRepository;
import com.pip.fitnessApplication.repository.UserRepository;
import com.pip.fitnessApplication.entity.User;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutDTO postWorkout(WorkoutDTO workoutDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Workout workout = new Workout();

        workout.setDate(workoutDTO.getDate());
        workout.setType(workoutDTO.getType());
        workout.setDuration(workoutDTO.getDuration());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());
        workout.setUser(user);
        return workoutRepository.save(workout).getWorkoutDTO();

    }

    public List<WorkoutDTO> getWorkouts(Long userId) {
        List<Workout> workouts = workoutRepository.findAllByUserId(userId);

        return workouts.stream()
                .map(Workout::getWorkoutDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWorkout(Long id) {
        workoutRepository.deleteById(id);
    }
}
