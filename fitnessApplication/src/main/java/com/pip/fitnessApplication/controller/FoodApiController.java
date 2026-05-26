package com.pip.fitnessApplication.controller;

import com.pip.fitnessApplication.dto.FoodResponseDto;
import com.pip.fitnessApplication.dto.FoodDto;
import com.pip.fitnessApplication.services.food.FoodApiService;
import com.pip.fitnessApplication.services.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FoodApiController {

    
    private final FoodApiService foodApiService;
    
    
    private final FoodService foodService;

    
    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchFood(@PathVariable String query) {
        FoodResponseDto result = foodApiService.searchFood(query);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body("Alimentul nu a fost găsit în baza de date Edamam.");
        }
    }

    
    @PostMapping("/{userId}")
    public ResponseEntity<?> postFood(@PathVariable Long userId, @RequestBody FoodDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(foodService.postFood(dto, userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFoods(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(foodService.getFoods(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la citirea alimentelor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la stergere");
        }
    }
}