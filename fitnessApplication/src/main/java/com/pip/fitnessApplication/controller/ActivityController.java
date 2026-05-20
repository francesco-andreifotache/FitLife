package com.pip.fitnessApplication.controller;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.services.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/activity/{userId}")
    public ResponseEntity<?> postActivity(@PathVariable Long userId, @RequestBody ActivityDto dto){
        ActivityDto createActivity = activityService.postActivity(dto, userId);

        if(createActivity != null){
            return  ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @GetMapping("/activities/{userId}")
    public ResponseEntity<?> getActivities(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(activityService.getActivities(userId));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la ștergerea activității");
        }
    }
}
