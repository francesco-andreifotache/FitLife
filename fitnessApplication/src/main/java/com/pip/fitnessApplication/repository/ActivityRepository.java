package com.pip.fitnessApplication.repository;

import com.pip.fitnessApplication.entity.Activity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository // face legatura cu baza de date
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByUserId(Long userId);

    @Query("SELECT SUM(a.steps) FROM Activity a WHERE a.user.id = :userId")
    Integer getTotalSteps(@Param("userId") Long userId);

    @Query("SELECT SUM(a.distance) FROM Activity a WHERE a.user.id = :userId")
    Double getTotalDistance(@Param("userId") Long userId);

    @Query("SELECT SUM(a.caloriesBurned) FROM Activity a WHERE a.user.id = :userId")
    Integer getTotalCaloriesBurned(@Param("userId") Long userId);

    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId ORDER BY a.date DESC")
    List<Activity> findLast7Activities(@Param("userId") Long userId, Pageable pageable);
}
