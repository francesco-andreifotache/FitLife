package com.pip.fitnessApplication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.pip.fitnessApplication.dto.GoalDto;
import com.pip.fitnessApplication.services.goal.GoalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GoalController {
    private final GoalService goalService;

    @PostMapping("/goal/{userId}")
    public ResponseEntity<?> postGoal(@PathVariable Long userId, @RequestBody GoalDto dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(goalService.postGoal(dto, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/goals/{userId}")
    public ResponseEntity<?> getGoals(@PathVariable Long userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(goalService.getGoal(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/goal/status/{id}/{userId}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable Long userId){
        try{
            return ResponseEntity.ok(goalService.updateStatus(id, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }  

    @DeleteMapping("/goal/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        try {
            goalService.deleteGoal(id);
            return ResponseEntity.ok().build(); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare");
        }
    }

}