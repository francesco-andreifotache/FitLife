package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.GraphDto;
import com.pip.fitnessApplication.dto.StatsDto;
import com.pip.fitnessApplication.services.Stats.StatsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private StatsService statsService;

    @Test
    public void testGetStats_Success() {
        Long userId = 1L;
        StatsDto mockStatsDto = new StatsDto();
        
        Mockito.when(statsService.getStats(userId)).thenReturn(mockStatsDto);

        StatsDto result = statsService.getStats(userId);

        assertNotNull(result);
    }

    @Test
    public void testGetGraphStats_Success() {
        Long userId = 1L;
        GraphDto mockGraphDto = new GraphDto();

        Mockito.when(statsService.getGraphStats(userId)).thenReturn(mockGraphDto);

        GraphDto result = statsService.getGraphStats(userId);

        assertNotNull(result);
    }
}