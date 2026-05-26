package com.pip.fitnessApplication.services.Stats;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pip.fitnessApplication.repository.ActivityRepository;
import com.pip.fitnessApplication.repository.GoalRepository;
import com.pip.fitnessApplication.repository.WorkoutRepository;
import com.pip.fitnessApplication.dto.GraphDto;
import com.pip.fitnessApplication.dto.StatsDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.entity.Workout;

import lombok.RequiredArgsConstructor;

/**
 * Serviciu care implementează logica de business pentru generarea statisticilor 
 * utilizatorilor în cadrul aplicației de fitness.
 * <p>
 * Această clasă colectează, procesează și agregă date din mai multe surse (obiective, 
 * activități zilnice și antrenamente efectuate) pentru a oferi o imagine de ansamblu 
 * asupra progresului fizic al unui utilizator.
 * </p>
 * * @author Alex
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService {

    /** Repository pentru gestionarea obiectivelor stabilite de utilizatori. */
    private final GoalRepository goalRepository;

    /** Repository pentru monitorizarea activităților zilnice (număr pași, distanță). */
    private final ActivityRepository activityRepository;

    /** Repository pentru evidența antrenamentelor și duratei acestora. */
    private final WorkoutRepository workoutRepository;

    /**
     * Calculează și compilează statisticile generale cumulate ale unui utilizator.
     * <p>
     * Metoda adună numărul de obiective îndeplinite/neîndeplinite, pașii totali, 
     * distanța parcursă, timpul total investit în antrenamente și cumulează caloriile 
     * arse atât din activități de rutină, cât și din sesiuni de workout dedicate.
     * </p>
     * * @param userId Identificatorul unic al utilizatorului pentru care se generează statisticile.
     * @return Un obiect {@link StatsDto} populat cu valorile agregate sau valori implicite (0/0.0) 
     * în cazul în care nu există înregistrări în baza de date.
     */
    public StatsDto getStats(Long userId){
        Long achivedGoals = goalRepository.countAchievedGoals(userId);
        Long notAchivedGoals = goalRepository.countNotAchievedGoals(userId);

        Integer totalSteps = activityRepository.getTotalSteps(userId);
        Double totalDistance = activityRepository.getTotalDistance(userId);
        Integer totalActivityCaloriesBurned = activityRepository.getTotalCaloriesBurned(userId);
        Integer totalDuration = workoutRepository.getTotalDuration(userId);
        Integer totalWorkoutCaloriesBurned = workoutRepository.getTotalCaloriesBurned(userId);

        int totalCaloriesBurned = (totalActivityCaloriesBurned != null ? totalActivityCaloriesBurned : 0) + 
                                  (totalWorkoutCaloriesBurned != null ? totalWorkoutCaloriesBurned : 0);

        StatsDto dto = new StatsDto();
        dto.setAchivedGaols(achivedGoals != null ? achivedGoals : 0);
        dto.setNotAchivedGoals(notAchivedGoals != null ? notAchivedGoals : 0);
        dto.setSteps(totalSteps != null ? totalSteps : 0);
        dto.setDistance(totalDistance != null ? totalDistance : 0.0);
        dto.setTotalCaloriesBurned(totalCaloriesBurned);
        dto.setDuration(totalDuration != null ? totalDuration : 0);
        return dto;
    }

    /**
     * Extrage ultimele șapte înregistrări de antrenamente și activități ale utilizatorului 
     * pentru a fi transpuse sub formă grafică în interfața utilizatorului.
     * <p>
     * Datele preluate din entitățile JPA sunt mapate direct în obiectele lor corespunzătoare 
     * de tip Data Transfer Object (DTO) folosind stream-uri Java.
     * </p>
     * * @param userId Identificatorul unic al utilizatorului solicitat.
     * @return Un obiect {@link GraphDto} ce conține două liste distincte: una pentru ultimele 7 antrenamente 
     * și una pentru ultimele 7 activități zilnice.
     */
    public GraphDto getGraphStats(Long userId){
        Pageable pageable = PageRequest.of(0, 7);

        List<Workout> workouts = workoutRepository.findLast7Workouts(userId, pageable);
        List<Activity> activities = activityRepository.findLast7Activities(userId, pageable);

        GraphDto graphDto = new GraphDto();
        graphDto.setWorkouts(workouts.stream().map(Workout::getWorkoutDTO).toList());
        graphDto.setActivities(activities.stream().map(Activity::getActivityDto).toList());
        return graphDto;
    }
}