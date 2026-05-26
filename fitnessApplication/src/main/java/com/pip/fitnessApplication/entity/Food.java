package com.pip.fitnessApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import com.pip.fitnessApplication.dto.FoodDto;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public FoodDto getFoodDto() {
        FoodDto dto = new FoodDto();
        dto.setId(id);
        dto.setName(name);
        dto.setCalories(calories);
        dto.setProtein(protein);
        dto.setCarbs(carbs);
        dto.setFat(fat);
        dto.setDate(date);
        return dto;
    }
}