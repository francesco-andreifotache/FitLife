package com.pip.fitnessApplication.services.goal;

import com.pip.fitnessApplication.dto.GoalDto;

public interface GoalService {
    GoalDto postGoal(GoalDto goalDto);
}