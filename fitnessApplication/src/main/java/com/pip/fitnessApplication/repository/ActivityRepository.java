package com.pip.fitnessApplication.repository;

import com.pip.fitnessApplication.entity.Activity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository JPA responsabil pentru gestionarea persistenței datelor din tabelul "Activity".
 * <p>
 * Extinde {@link JpaRepository} oferind operații standard de tip CRUD, adăugând în același timp
 * metode personalizate pentru extragerea și agregarea statisticilor legate de activitățile fizice
 * ale unui utilizator (pași, distanță, calorii arse).
 * </p>
 * * @author Alex
 * @version 1.0
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * Extrage toate activitățile fizice înregistrate în baza de date pentru un anumit utilizator.
     * * @param userId Identificatorul unic al utilizatorului.
     * @return O listă completă de entități {@link Activity} asociate acelui utilizator.
     */
    List<Activity> findAllByUserId(Long userId);

    /**
     * Calculează suma totală a pașilor efectuați de un utilizator în toate activitățile sale.
     * * @param userId Identificatorul unic al utilizatorului.
     * @return Numărul total de pași sub formă de {@link Integer}, sau {@code null} dacă nu există înregistrări.
     */
    @Query("SELECT SUM(a.steps) FROM Activity a WHERE a.user.id = :userId")
    Integer getTotalSteps(@Param("userId") Long userId);

    /**
     * Calculează suma totală a distanței parcurse de un utilizator.
     * * @param userId Identificatorul unic al utilizatorului.
     * @return Distanța totală acumulată ca {@link Double}, sau {@code null} dacă nu există înregistrări.
     */
    @Query("SELECT SUM(a.distance) FROM Activity a WHERE a.user.id = :userId")
    Double getTotalDistance(@Param("userId") Long userId);

    /**
     * Calculează suma totală a caloriilor arse de un utilizator de-a lungul tuturor activităților.
     * * @param userId Identificatorul unic al utilizatorului.
     * @return Numărul total de calorii arse ca {@link Integer}, sau {@code null} dacă nu există înregistrări.
     */
    @Query("SELECT SUM(a.caloriesBurned) FROM Activity a WHERE a.user.id = :userId")
    Integer getTotalCaloriesBurned(@Param("userId") Long userId);

    /**
     * Extrage un set limitat de activități recente ale utilizatorului, ordonate cronologic descrescător.
     * <p>
     * Această metodă folosește {@link Pageable} pentru a implementa paginarea direct din baza de date 
     * (de exemplu, pentru a prelua exact ultimele 7 înregistrări).
     * </p>
     * * @param userId Identificatorul unic al utilizatorului.
     * @param pageable Obiect de configurare a paginării și limitării rezultatelor (ex: PageRequest.of(0, 7)).
     * @return O listă conținând ultimele activități ale utilizatorului conform criteriilor de paginare.
     */
    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId ORDER BY a.date DESC")
    List<Activity> findLast7Activities(@Param("userId") Long userId, Pageable pageable);
}