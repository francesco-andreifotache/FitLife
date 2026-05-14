package com.pip.fitnessApplication.services.goal;

import com.pip.fitnessApplication.dto.GoalDto;
import java.util.List;

public interface GoalService {
    GoalDto postGoal(GoalDto goalDto);
    List<GoalDto> getGoal();
    GoalDto updateStatus(Long id);
}