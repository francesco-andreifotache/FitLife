package com.pip.fitnessApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pip.fitnessApplication.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long>{
    
}
