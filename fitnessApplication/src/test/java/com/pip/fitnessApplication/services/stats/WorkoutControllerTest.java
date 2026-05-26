package com.pip.fitnessApplication.services.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pip.fitnessApplication.controller.WorkoutController;
import com.pip.fitnessApplication.dto.WorkoutDTO;
import com.pip.fitnessApplication.services.workout.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class WorkoutControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WorkoutService workoutService;

    @InjectMocks
    private WorkoutController workoutController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(workoutController).build();
    }

    @Test
    public void testPostWorkout_Success() throws Exception {
        Long userId = 1L;
        WorkoutDTO inputDto = new WorkoutDTO();
        inputDto.setType("Cardio");
        inputDto.setDuration(45);

        WorkoutDTO savedDto = new WorkoutDTO();
        savedDto.setId(10L);
        savedDto.setType("Cardio");
        savedDto.setDuration(45);

        Mockito.when(workoutService.postWorkout(Mockito.any(WorkoutDTO.class), Mockito.eq(userId))).thenReturn(savedDto);

        mockMvc.perform(post("/api/workout/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.type").value("Cardio"))
                .andExpect(jsonPath("$.duration").value(45));
    }

    @Test
    public void testPostWorkout_Failure() throws Exception {
        Long userId = 1L;
        WorkoutDTO inputDto = new WorkoutDTO();

        Mockito.when(workoutService.postWorkout(Mockito.any(WorkoutDTO.class), Mockito.eq(userId)))
                .thenThrow(new RuntimeException("Error saving workout"));

        mockMvc.perform(post("/api/workout/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Something went wrong"));
    }

    @Test
    public void testGetWorkouts_Success() throws Exception {
        Long userId = 1L;
        List<WorkoutDTO> mockList = List.of(new WorkoutDTO(), new WorkoutDTO());

        Mockito.when(workoutService.getWorkouts(userId)).thenReturn(mockList);

        mockMvc.perform(get("/api/workouts/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testGetWorkouts_Failure() throws Exception {
        Long userId = 1L;
        Mockito.when(workoutService.getWorkouts(userId)).thenThrow(new RuntimeException("Fetch error"));

        mockMvc.perform(get("/api/workouts/{userId}", userId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Something went wrong"));
    }

    @Test
    public void testDeleteWorkout_Success() throws Exception {
        Long workoutId = 99L;
        Mockito.doNothing().when(workoutService).deleteWorkout(workoutId);

        mockMvc.perform(delete("/api/workout/{id}", workoutId))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteWorkout_Failure() throws Exception {
        Long workoutId = 99L;
        Mockito.doThrow(new RuntimeException("Delete error")).when(workoutService).deleteWorkout(workoutId);

        mockMvc.perform(delete("/api/workout/{id}", workoutId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Eroare la stergerea workout-ului"));
    }
}