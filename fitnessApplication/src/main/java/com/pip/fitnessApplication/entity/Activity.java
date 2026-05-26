package com.pip.fitnessApplication.entity;

import com.pip.fitnessApplication.dto.ActivityDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.util.Date;

/**
 * Entitate JPA care mapează tabelul "activity" din baza de date MySQL.
 * <p>
 * Această clasă stochează informațiile persistente despre activitatea fizică a unui utilizator.
 * Include o relație de tip Many-to-One cu entitatea {@link User} și o metodă utilitară 
 * pentru conversia datelor interne într-un obiect de tip DTO decuplat.
 * </p>
 * * @author Alex
 * @version 1.0
 */
@Entity
@Data
public class Activity {

    /** Identificatorul unic al activității (Cheie Primară), generat automat prin strategie de tip IDENTITY. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Data la care a fost realizată activitatea fizică. */
    private Date date;

    /** Numărul total de pași înregistrați. */
    private int steps;

    /** Distanța totală parcursă în cadrul activității. */
    private int distance;

    /** Numărul de calorii arse în timpul activității. */
    private int caloriesBurned;

    /**
     * Relație de tip Many-to-One către utilizatorul posesor al acestei activități.
     * <p>
     * Încărcarea relației este configurată ca {@link FetchType#LAZY} pentru optimizarea performanței, 
     * iar existența unui utilizator valid asociat este obligatorie (optional = false).
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Metodă utilitară care convertește starea curentă a entității persistente 
     * într-un obiect de transfer de date {@link ActivityDto}.
     * <p>
     * Această conversie este esențială pentru a evita expunerea directă a modelelor de bază de date către straturile superioare (Controller/Frontend).
     * </p>
     * * @return Un obiect {@link ActivityDto} populat cu datele din această entitate.
     */
    public ActivityDto getActivityDto(){
        ActivityDto activityDto = new ActivityDto();

        activityDto.setId(id);
        activityDto.setDate(date);
        activityDto.setSteps(steps);
        activityDto.setDistance(distance);
        activityDto.setCaloriesBurned(caloriesBurned);

        return activityDto;
    }
}
