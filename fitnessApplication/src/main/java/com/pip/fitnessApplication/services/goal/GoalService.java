package com.pip.fitnessApplication.services.goal;

import com.pip.fitnessApplication.dto.GoalDto;
import java.util.List;

public interface GoalService {
    GoalDto postGoal(GoalDto goalDto, Long userId);
    List<GoalDto> getGoal(Long userId);
    GoalDto updateStatus(Long id, Long userId);

    void deleteGoal(Long id);
}