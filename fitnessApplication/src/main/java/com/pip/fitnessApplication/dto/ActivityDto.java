package com.pip.fitnessApplication.dto;

import lombok.Data;
import java.util.Date;

/**
 * Obiect de transfer al datelor (Data Transfer Object) utilizat pentru a trimite și primi 
 * informații despre activitățile fizice ale utilizatorilor prin intermediul API-ului REST.
 * <p>
 * Această clasă încapsulează detalii precum numărul de pași, distanța parcursă și caloriile arse.
 * Reducerea codului boiler-plate (gettere, settere, toString, equals) este realizată automat prin adnotarea Lombok.
 * </p>
 * * @author Alex
 * @version 1.0
 */
@Data
public class ActivityDto {

    /** Identificatorul unic al activității în baza de date. */
    private Long id;

    /** Data la care a fost înregistrată activitatea fizică. */
    private Date date;

    /** Numărul total de pași efectuați în cadrul activității. */
    private int steps;

    /** Distanța parcursă, exprimată în kilometri sau metri (în funcție de convenția aplicației). */
    private int distance;

    /** Numărul estimat de calorii arse în timpul acestei activități. */
    private int caloriesBurned;

    /** Identificatorul unic al utilizatorului de care aparține această activitate. */
    private Long userId;
}