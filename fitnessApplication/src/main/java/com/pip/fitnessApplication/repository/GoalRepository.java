package com.pip.fitnessApplication.repository;

import org.springframework.stereotype.Repository;
import com.pip.fitnessApplication.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    
}