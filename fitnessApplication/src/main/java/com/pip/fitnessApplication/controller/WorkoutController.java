package com.pip.fitnessApplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.pip.fitnessApplication.dto.WorkoutDTO;
import com.pip.fitnessApplication.services.workout.WorkoutService;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorkoutController {
    
    private final WorkoutService workoutService;

    @PostMapping("/workout/{userId}")
    public ResponseEntity<?> postWorkout(@PathVariable Long userId, @RequestBody WorkoutDTO dto){
        try{
            return ResponseEntity.ok(workoutService.postWorkout(dto, userId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/workouts/{userId}")
    public ResponseEntity<?> getWorkouts(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(workoutService.getWorkouts(userId));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/workout/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        try {
            workoutService.deleteWorkout(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la ștergerea workout-ului");
        }
    }
}