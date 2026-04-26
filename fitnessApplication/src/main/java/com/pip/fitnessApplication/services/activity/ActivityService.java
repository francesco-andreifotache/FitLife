package com.pip.fitnessApplication.services.activity;

import com.pip.fitnessApplication.dto.ActivityDto;

import java.util.List;

public interface ActivityService {
    ActivityDto postActivity(ActivityDto dto);

    List<ActivityDto> getActivities();
}
