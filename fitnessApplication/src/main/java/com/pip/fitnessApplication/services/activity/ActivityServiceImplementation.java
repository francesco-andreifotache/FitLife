package com.pip.fitnessApplication.services.activity;

import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.entity.Activity;
import com.pip.fitnessApplication.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementarea serviciului {@link ActivityService} responsabilă de gestionarea logicii de business
 * pentru activitățile fizice ale utilizatorilor.
 * <p>
 * Această clasă asigură validarea utilizatorilor, salvarea activităților noi,
 * preluarea istoricului de activități și ștergerea acestora prin delegarea operațiilor către straturile de persistență.
 * </p>
 *
 * @author Alex
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImplementation implements ActivityService {

    /** Repository pentru manipularea datelor persistente ale activităților. */
    private final ActivityRepository activityRepository; 

    /** Repository pentru verificarea și obținerea datelor despre utilizatori. */
    private final UserRepository userRepository;

    /**
     * Înregistrează o nouă activitate în sistem pentru un utilizator specificat.
     * <p>
     * Metoda caută mai întâi utilizatorul în baza de date. Dacă acesta există, mapează datele din DTO
     * într-o entitate {@link Activity}, stabilește relația bidirecțională și salvează înregistrarea.
     * </p>
     *
     * @param dto Datele activității fizice transmise de client.
     * @param userId Identificatorul unic al utilizatorului care realizează activitatea.
     * @return Un obiect {@link ActivityDto} ce reprezintă activitatea salvată cu succes (inclusiv ID-ul generat).
     * @throws RuntimeException Dacă utilizatorul cu ID-ul specificat nu a fost găsit în baza de date.
     */
    public ActivityDto postActivity(ActivityDto dto, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        activity.setUser(user);

        return activityRepository.save(activity).getActivityDto();
    }

    /**
     * Returnează istoricul complet de activități fizice al unui anumit utilizator.
     * <p>
     * Datele sunt extrase sub formă de entități din baza de date și apoi transformate (mapate)
     * în obiecte DTO folosind Stream API pentru a fi transmise în siguranță către controller.
     * </p>
     *
     * @param userId Identificatorul unic al utilizatorului vizat.
     * @return O listă de obiecte {@link ActivityDto} ordonate conform regulilor de persistență.
     */
    public List<ActivityDto> getActivities(Long userId){
        List<Activity> activities = activityRepository.findAllByUserId(userId);
        return activities.stream().map(Activity::getActivityDto).collect(Collectors.toList());
    }

    /**
     * Șterge definitiv o activitate din baza de date pe baza ID-ului acesteia.
     *
     * @param id Identificatorul unic al activității ce urmează a fi eliminată.
     */
    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}