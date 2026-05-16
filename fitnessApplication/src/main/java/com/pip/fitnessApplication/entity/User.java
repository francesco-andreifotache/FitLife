package com.pip.fitnessApplication.entity;


import jakarta.persistence.*;
import lombok.Data;
import com.pip.fitnessApplication.dto.UserDTO;

@Entity
@Data
@Table(name = "users") // Folosim "users" pt că "user" este un cuvânt rezervat în MySQL și dă eroare
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    // Aici vom adăuga rolul (ex: ADMIN sau USER), util pentru mai târziu
    private String role;

    // O metodă rapidă de conversie, așa cum am făcut și la Workout
    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setName(this.name);
        userDTO.setEmail(this.email);
        userDTO.setRole(this.role);
        // NU trimitem parola în DTO pentru securitate!
        return userDTO;
    }
}