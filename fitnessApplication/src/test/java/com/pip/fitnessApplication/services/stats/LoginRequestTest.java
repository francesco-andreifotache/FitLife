package com.pip.fitnessApplication.services.stats;

import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.dto.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginRequestTest {

    @Test
    public void testGettersAndSetters() {
        
        LoginRequest request = new LoginRequest();
        String testEmail = "alex@example.com";
        String testPassword = "SecurePassword123!";

        
        request.setEmail(testEmail);
        request.setPassword(testPassword);

        
        assertEquals(testEmail, request.getEmail(), "Email-ul setat nu coincide cu cel returnat.");
        assertEquals(testPassword, request.getPassword(), "Parola setată nu coincide cu cea returnată.");
    }

    @Test
    public void testToStringAndEquals() {
        
        LoginRequest req1 = new LoginRequest();
        req1.setEmail("user@test.com");
        req1.setPassword("1234");

        LoginRequest req2 = new LoginRequest();
        req2.setEmail("user@test.com");
        req2.setPassword("1234");

        
        assertEquals(req1, req2, "Doua instante cu aceleasi date ar trebui sa fie egale.");
        assertNotNull(req1.toString(), "Metoda toString() nu ar trebui sa returneze null.");
    }
}