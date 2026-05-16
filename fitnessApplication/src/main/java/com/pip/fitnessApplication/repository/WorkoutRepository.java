package com.pip.fitnessApplication.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.pip.fitnessApplication.entity.Workout;
import org.springframework.data.repository.query.Param;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{

    List<Workout> findAllByUserId(Long userId);

    @Query("SELECT SUM(w.duration) FROM Workout w WHERE w.user.id = :userId")
    Integer getTotalDuration(@Param("userId") Long userId);

    @Query("SELECT SUM(w.caloriesBurned) FROM Workout w WHERE w.user.id = :userId")
    Integer getTotalCaloriesBurned(@Param("userId") Long userId);

    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId ORDER BY w.date DESC")
    List<Workout> findLast7Workouts(@Param("userId") Long userId, Pageable pageable);
}
