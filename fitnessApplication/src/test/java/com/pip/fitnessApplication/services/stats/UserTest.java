package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.dto.UserDTO;
import com.pip.fitnessApplication.entity.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
       
        User user = new User();
        Long testId = 10L;
        String testName = "Alex Popescu";
        String testPassword = "secret_password_hash";
        String testEmail = "alex.p@test.com";
        String testRole = "USER";

        
        user.setId(testId);
        user.setName(testName);
        user.setPassword(testPassword);
        user.setEmail(testEmail);
        user.setRole(testRole);

       
        assertEquals(testId, user.getId());
        assertEquals(testName, user.getName());
        assertEquals(testPassword, user.getPassword());
        assertEquals(testEmail, user.getEmail());
        assertEquals(testRole, user.getRole());
    }

    @Test
    public void testGetUserDTO_ShouldMapCorrectlyAndExcludePassword() {
        
        User user = new User();
        user.setId(1L);
        user.setName("Andrei");
        user.setEmail("andrei@fitness.ro");
        user.setPassword("parolaMeaSecurizata123");
        user.setRole("ADMIN");

      
        UserDTO dto = user.getUserDTO();

        
        assertNotNull(dto, "Obiectul DTO nu ar trebui sa fie null");
        assertEquals(user.getId(), dto.getId(), "ID-ul nu se potriveste");
        assertEquals(user.getName(), dto.getName(), "Numele nu se potriveste");
        assertEquals(user.getEmail(), dto.getEmail(), "Email-ul nu se potriveste");
        assertEquals(user.getRole(), dto.getRole(), "Rolul nu se potriveste");
    }
}