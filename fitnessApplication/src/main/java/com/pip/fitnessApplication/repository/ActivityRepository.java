package com.pip.fitnessApplication.repository;

import com.pip.fitnessApplication.entity.Activity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository // face legatura cu baza de date
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT SUM(a.steps) FROM Activity a")
    Integer getTotalSteps();

    @Query("SELECT SUM(a.distance) FROM Activity a")
    Double getTotalDistance();

    @Query("SELECT SUM(a.caloriesBurned) FROM Activity a")
    Integer getTotalCaloriesBurned();

    @Query("SELECT a FROM Activity a ORDER BY a.date DESC")
    List<Activity> findLast7Activities(Pageable pageable);
}
