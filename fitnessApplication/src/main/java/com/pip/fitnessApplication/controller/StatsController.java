package com.pip.fitnessApplication.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.pip.fitnessApplication.dto.GraphDto;
import com.pip.fitnessApplication.services.Stats.StatsService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StatsController {
    
    private final StatsService statsService;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(statsService.getStats());
    }

    @GetMapping("/graphs")
    public ResponseEntity<?> getGraphStats() {
        GraphDto graphDto = statsService.getGraphStats();

        if(graphDto != null){
            return ResponseEntity.ok(graphDto);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
