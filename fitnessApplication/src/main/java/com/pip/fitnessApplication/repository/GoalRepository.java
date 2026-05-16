package com.pip.fitnessApplication.repository;

import org.springframework.stereotype.Repository;
import com.pip.fitnessApplication.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    
    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = true")
    Long countAchievedGoals();

    @Query("SELECT COUNT(g) FROM Goal g WHERE g.achieved = false")
    Long countNotAchievedGoals();
}