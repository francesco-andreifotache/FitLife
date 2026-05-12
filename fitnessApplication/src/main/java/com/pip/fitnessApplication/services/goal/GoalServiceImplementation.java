package com.pip.fitnessApplication.services.goal;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.dto.GoalDto;
import com.pip.fitnessApplication.entity.Goal;


@Service
@RequiredArgsConstructor
public class GoalServiceImplementation implements GoalService {
    private final GoalRepository goalRepository;

    public GoalDto postGoal(GoalDto goalDto){
        Goal goal = new Goal();

        goal.setDescription(goalDto.getDescription());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setAchieved(false);

        return goalRepository.save(goal).getGoalDto();
    }
}