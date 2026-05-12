package com.pip.fitnessApplication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import com.pip.fitnessApplication.dto.GoalDto;
import com.pip.fitnessApplication.services.goal.GoalService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GoalController {
    private final GoalService goalService;

    @PostMapping("/goal")
    public ResponseEntity<?> postGoal(@RequestBody GoalDto dto){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(goalService.postGoal(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
}
}