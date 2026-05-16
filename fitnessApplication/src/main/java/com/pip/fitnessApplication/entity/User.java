package com.pip.fitnessApplication.entity;


import jakarta.persistence.*;
import lombok.Data;
import com.pip.fitnessApplication.dto.UserDTO;

@Entity
@Data
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    private String role;

    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setName(this.name);
        userDTO.setEmail(this.email);
        userDTO.setRole(this.role);
        return userDTO;
    }
}