package com.pip.fitnessApplication.services.activity;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImplementation implements ActivityService{
    private final ActivityRepository activityRepository; // conexiunea cu baza de date CRUD

    public ActivityDto postActivity(ActivityDto dto){
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        return activityRepository.save(activity).getActivityDto();
    }

    public List<ActivityDto> getActivities(){
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(Activity::getActivityDto).collect(Collectors.toList());
    }
}
