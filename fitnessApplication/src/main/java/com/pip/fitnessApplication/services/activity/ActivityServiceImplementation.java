package com.pip.fitnessApplication.services.activity;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImplementation implements ActivityService{
    private final ActivityRepository activityRepository; 
    private final UserRepository userRepository;

    public ActivityDto postActivity(ActivityDto dto, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        activity.setUser(user);

        return activityRepository.save(activity).getActivityDto();
    }

    public List<ActivityDto> getActivities(Long userId){
        List<Activity> activities = activityRepository.findAllByUserId(userId);
        return activities.stream().map(Activity::getActivityDto).collect(Collectors.toList());
    }
}
