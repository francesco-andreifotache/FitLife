package com.pip.fitnessApplication.services.food;

import com.pip.fitnessApplication.dto.FoodResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodApiService {

    
    @Value("${edamam.api.url}")
    private String apiUrl;

    @Value("${edamam.api.appId}")
    private String appId;

    @Value("${edamam.api.appKey}")
    private String appKey;

    public FoodResponseDto searchFood(String query) {
        
        String url = apiUrl + "?app_id=" + appId + "&app_key=" + appKey + "&ingr=" + query;

        RestTemplate restTemplate = new RestTemplate();

        try {
            
            String jsonResponse = restTemplate.getForObject(url, String.class);

            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            
            JsonNode hints = root.path("hints");
            if (hints.isArray() && hints.size() > 0) {
                
                JsonNode foodNode = hints.get(0).path("food");
                JsonNode nutrients = foodNode.path("nutrients");

                
                FoodResponseDto dto = new FoodResponseDto();
                dto.setName(foodNode.path("label").asText());

                
                dto.setCalories(Math.round(nutrients.path("ENERC_KCAL").asDouble()));
                dto.setProtein(Math.round(nutrients.path("PROCNT").asDouble() * 100.0) / 100.0);
                dto.setFat(Math.round(nutrients.path("FAT").asDouble() * 100.0) / 100.0);
                dto.setCarbs(Math.round(nutrients.path("CHOCDF").asDouble() * 100.0) / 100.0);

                return dto;
            }
        } catch (Exception e) {
            System.out.println("Eroare la comunicarea cu Edamam: " + e.getMessage());
        }

        return null; 
    }
}