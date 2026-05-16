package com.pip.fitnessApplication.services.Stats;

import com.pip.fitnessApplication.dto.GraphDto;
import com.pip.fitnessApplication.dto.StatsDto;

public interface StatsService {
    StatsDto getStats();
    GraphDto getGraphStats();
}
