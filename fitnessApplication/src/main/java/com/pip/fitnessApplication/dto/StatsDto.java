package com.pip.fitnessApplication.dto;

import lombok.Data;

/**
 * Obiect de Transfer de Date (DTO) utilizat pentru a transporta statisticile generale 
 * agregate ale unui utilizator din stratul de backend către interfața grafică (Frontend).
 * <p>
 * Clasa folosește adnotarea Lombok {@link Data} pentru a genera automat 
 * getter-ele, setter-ele, metodele toString, equals și hashCode.
 * </p>
 * * @author Alex
 * @version 1.0
 */
@Data
public class StatsDto {
    
    /** * Numărul total de obiective pe care utilizatorul le-a îndeplinit cu succes.
     */
    private Long achivedGaols;

    /** * Numărul total de obiective active sau eșuate care nu au fost încă îndeplinite.
     */
    private Long notAchivedGoals;

    /** * Numărul total de pași efectuați de utilizator prin activitățile înregistrate.
     */
    private int steps;

    /** * Distanța totală parcursă de utilizator, exprimată în kilometri.
     */
    private Double distance;

    /** * Cantitatea totală de calorii arse, calculată prin însumarea caloriilor 
     * din activitățile zilnice cu cele din antrenamentele (workouts) efectuate.
     */
    private int totalCaloriesBurned;

    /** * Durata totală cumulată a antrenamentelor efectuate de utilizator, exprimată în minute.
     */
    private int duration;
}