package com.pip.fitnessApplication.services.food;

import com.pip.fitnessApplication.dto.FoodResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodApiService {

    // Citim cheile din application.properties în mod securizat
    @Value("${edamam.api.url}")
    private String apiUrl;

    @Value("${edamam.api.appId}")
    private String appId;

    @Value("${edamam.api.appKey}")
    private String appKey;

    public FoodResponseDto searchFood(String query) {
        // Construim link-ul complet către Edamam
        String url = apiUrl + "?app_id=" + appId + "&app_key=" + appKey + "&ingr=" + query;

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Facem cererea GET către Edamam și primim răspunsul sub formă de text (JSON)
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // Folosim ObjectMapper pentru a naviga prin structura JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            // Edamam returnează rezultatele într-o listă numită "hints"
            JsonNode hints = root.path("hints");
            if (hints.isArray() && hints.size() > 0) {
                // Luăm primul rezultat relevant
                JsonNode foodNode = hints.get(0).path("food");
                JsonNode nutrients = foodNode.path("nutrients");

                // Împachetăm datele în DTO-ul nostru curat
                FoodResponseDto dto = new FoodResponseDto();
                dto.setName(foodNode.path("label").asText());

                // Edamam trimite datele per 100g sau per porție standard. Extragem macro-urile:
                dto.setCalories(Math.round(nutrients.path("ENERC_KCAL").asDouble()));
                dto.setProtein(Math.round(nutrients.path("PROCNT").asDouble() * 100.0) / 100.0);
                dto.setFat(Math.round(nutrients.path("FAT").asDouble() * 100.0) / 100.0);
                dto.setCarbs(Math.round(nutrients.path("CHOCDF").asDouble() * 100.0) / 100.0);

                return dto;
            }
        } catch (Exception e) {
            System.out.println("Eroare la comunicarea cu Edamam: " + e.getMessage());
        }

        return null; // Returnăm null dacă nu a găsit alimentul
    }
}