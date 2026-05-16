package com.pip.fitnessApplication.repository;

import org.springframework.stereotype.Repository;

import com.pip.fitnessApplication.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<Goal> findAllByUserId(Long userId);

    @Query("SELECT COUNT(g) FROM Goal g WHERE (g.achieved = true AND g.user.id = :userId)")
    Long countAchievedGoals(@Param("userId") Long userId);

    @Query("SELECT COUNT(g) FROM Goal g WHERE (g.achieved = false AND g.user.id = :userId)")
    Long countNotAchievedGoals(@Param("userId") Long userId);
}