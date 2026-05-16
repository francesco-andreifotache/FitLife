package com.pip.fitnessApplication.services.goal;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.repository.UserRepository;
import com.pip.fitnessApplication.entity.User;
import jakarta.persistence.EntityNotFoundException;

import com.pip.fitnessApplication.dto.GoalDto;
import com.pip.fitnessApplication.entity.Goal;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class GoalServiceImplementation implements GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalDto postGoal(GoalDto goalDto, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Goal goal = new Goal();

        goal.setDescription(goalDto.getDescription());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setAchieved(false);
        goal.setUser(user);

        return goalRepository.save(goal).getGoalDto();
    }

    public List<GoalDto> getGoal(Long userId){
        List<Goal> goals = goalRepository.findAllByUserId(userId);

        return goals.stream().map(Goal::getGoalDto).toList();
    }

    public GoalDto updateStatus(Long id, Long userId){
        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if(optionalGoal.isPresent()){
            Goal existingGoal = optionalGoal.get();
            if(!existingGoal.getUser().getId().equals(userId)){
                throw new RuntimeException("User not found");
            }
            existingGoal.setAchieved(true);
            return goalRepository.save(existingGoal).getGoalDto();
        }
        throw new EntityNotFoundException("Goal not found");
    }
}