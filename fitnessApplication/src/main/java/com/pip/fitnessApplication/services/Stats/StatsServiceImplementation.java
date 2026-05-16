package com.pip.fitnessApplication.services.Stats;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pip.fitnessApplication.repository.ActivityRepository;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.repository.WorkoutRepository;
import com.pip.fitnessApplication.dto.GraphDto;
import com.pip.fitnessApplication.dto.StatsDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.entity.Workout;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService {
    private final GoalRepository goalRepository;

    private final ActivityRepository activityRepository;
    private final WorkoutRepository workoutRepository;

    public StatsDto getStats(Long userId){
        Long achivedGoals = goalRepository.countAchievedGoals(userId);
        Long notAchivedGoals = goalRepository.countNotAchievedGoals(userId);

        Integer totalSteps = activityRepository.getTotalSteps(userId);
        Double totalDistance = activityRepository.getTotalDistance(userId);
        Integer totalActivityCaloriesBurned = activityRepository.getTotalCaloriesBurned(userId);
        Integer totalDuration = workoutRepository.getTotalDuration(userId);
        Integer totalWorkoutCaloriesBurned = workoutRepository.getTotalCaloriesBurned(userId);

        int totalCaloriesBurned = (totalActivityCaloriesBurned != null ? totalActivityCaloriesBurned : 0) + 
                                  (totalWorkoutCaloriesBurned != null ? totalWorkoutCaloriesBurned : 0);

        StatsDto dto = new StatsDto();
        dto.setAchivedGaols(achivedGoals != null ? achivedGoals : 0);
        dto.setNotAchivedGoals(notAchivedGoals != null ? notAchivedGoals : 0);
        dto.setSteps(totalSteps != null ? totalSteps : 0);
        dto.setDistance(totalDistance != null ? totalDistance : 0.0);
        dto.setTotalCaloriesBurned(totalCaloriesBurned);
        dto.setDuration(totalDuration != null ? totalDuration : 0);
        return dto;
    }

    public GraphDto getGraphStats(Long userId){
        Pageable pageable = PageRequest.of(0, 7);

        List<Workout> workouts = workoutRepository.findLast7Workouts(userId, pageable);
        List<Activity> activities = activityRepository.findLast7Activities(userId, pageable);

        GraphDto graphDto = new GraphDto();
        graphDto.setWorkouts(workouts.stream().map(Workout::getWorkoutDTO).toList());
        graphDto.setActivities(activities.stream().map(Activity::getActivityDto).toList());
        return graphDto;
    }
}
