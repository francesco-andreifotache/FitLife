package com.pip.fitnessApplication.services.goal;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.pip.fitnessApplication.repository.GoalRepository;

import jakarta.persistence.EntityNotFoundException;

import com.pip.fitnessApplication.dto.GoalDto;
import com.pip.fitnessApplication.entity.Goal;
import java.util.List;
import java.util.Optional;



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

    public List<GoalDto> getGoal(){
        List<Goal> goals = goalRepository.findAll();

        return goals.stream().map(Goal::getGoalDto).toList();
    }

    public GoalDto updateStatus(Long id){
        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if(optionalGoal.isPresent()){
            Goal existingGoal = optionalGoal.get();
            existingGoal.setAchieved(true);
            return goalRepository.save(existingGoal).getGoalDto();
        }
        throw new EntityNotFoundException("Goal not found");
    }
}