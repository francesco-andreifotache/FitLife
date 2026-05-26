package com.pip.fitnessApplication.controller;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.services.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controler REST responsabil de gestionarea punctelor de acces (endpoints) pentru activitățile utilizatorilor.
 * <p>
 * Oferă operații de tip CRUD (creare, citire, ștergere) expuse sub prefixul de URL "/api".
 * Permite integrarea cu sisteme frontend prin activarea CORS pentru toate originile (*).
 * </p>
 * * @author Alex
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {

    /** Serviciul delegat pentru manipularea logicii de business a activităților. */
    private final ActivityService activityService;

    /**
     * Înregistrează o nouă activitate fizică în sistem asociată unui anumit utilizator.
     * * @param userId Identificatorul unic al utilizatorului care adaugă activitatea.
     * @param dto Datele activității preluate din corpul cererii HTTP (Request Body).
     * @return Un obiect {@link ResponseEntity} ce conține codul statusului HTTP 201 (CREATED) 
     * și corpul activității salvate, sau statusul 500 (INTERNAL_SERVER_ERROR) în caz de eșec.
     */
    @PostMapping("/activity/{userId}")
    public ResponseEntity<?> postActivity(@PathVariable Long userId, @RequestBody ActivityDto dto){
        ActivityDto createActivity = activityService.postActivity(dto, userId);

        if(createActivity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    /**
     * Extrage lista tuturor activităților înregistrate de un anumit utilizator.
     * * @param userId Identificatorul unic al utilizatorului solicitat.
     * @return Un obiect {@link ResponseEntity} cu statusul HTTP 200 (OK) și lista de activități aferente,
     * sau statusul 500 (INTERNAL_SERVER_ERROR) dacă intervine o excepție în sistem.
     */
    @GetMapping("/activities/{userId}")
    public ResponseEntity<?> getActivities(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(activityService.getActivities(userId));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    /**
     * Șterge o activitate existentă din baza de date pe baza identificatorului său.
     * * @param id Identificatorul unic al activității ce urmează a fi eliminată.
     * @return Un obiect {@link ResponseEntity} cu statusul HTTP 200 (OK) în caz de reușită,
     * sau statusul 500 (INTERNAL_SERVER_ERROR) însoțit de un mesaj text explicativ în caz de eroare.
     */
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la stergerea activitatii");
        }
    }
}