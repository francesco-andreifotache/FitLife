package com.pip.fitnessApplication.services.stats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pip.fitnessApplication.controller.ActivityController;
import com.pip.fitnessApplication.dto.ActivityDto;
import com.pip.fitnessApplication.services.activity.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ActivityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testPostActivity_Success() throws Exception {
        Long userId = 1L;
        ActivityDto inputDto = new ActivityDto();
        ActivityDto savedDto = new ActivityDto();

        Mockito.when(activityService.postActivity(Mockito.any(ActivityDto.class), Mockito.eq(userId))).thenReturn(savedDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/activity/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(activityService, Mockito.times(1)).postActivity(Mockito.any(ActivityDto.class), Mockito.eq(userId));
    }

    @Test
    public void testPostActivity_Failure() throws Exception {
        Long userId = 1L;
        ActivityDto inputDto = new ActivityDto();

        Mockito.when(activityService.postActivity(Mockito.any(ActivityDto.class), Mockito.eq(userId))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/activity/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Something went wrong"));
    }

    @Test
    public void testGetActivities_Success() throws Exception {
        Long userId = 1L;
        List<ActivityDto> list = List.of(new ActivityDto());

        Mockito.when(activityService.getActivities(userId)).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities/{userId}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(activityService, Mockito.times(1)).getActivities(userId);
    }

    @Test
    public void testGetActivities_Exception() throws Exception {
        Long userId = 1L;

        Mockito.when(activityService.getActivities(userId)).thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/activities/{userId}", userId))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Something went wrong"));
    }

    @Test
    public void testDeleteActivity_Success() throws Exception {
        Long activityId = 10L;

        Mockito.doNothing().when(activityService).deleteActivity(activityId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/activity/{id}", activityId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(activityService, Mockito.times(1)).deleteActivity(activityId);
    }

    @Test
    public void testDeleteActivity_Exception() throws Exception {
        Long activityId = 10L;

        Mockito.doThrow(new RuntimeException("Delete failed")).when(activityService).deleteActivity(activityId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/activity/{id}", activityId))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Eroare la stergerea activitatii"));
    }
}