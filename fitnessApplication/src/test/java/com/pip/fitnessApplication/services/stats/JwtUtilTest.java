package com.pip.fitnessApplication.services.stats;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pip.fitnessApplication.utils.JwtUtil;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken_ShouldReturnValidTokenString() {
        
        String username = "alex_fitness_master";

        
        String token = jwtUtil.generateToken(username);

        
        assertNotNull(token, "Token-ul generat nu ar trebui să fie null");
        assertFalse(token.isEmpty(), "Token-ul nu ar trebui să fie un string gol");
        
        
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "Un token JWT valid trebuie să aibă exact 3 secțiuni separate prin punct");
    }

    @Test
    public void testGenerateToken_ShouldContainCorrectSubjectAndExpiration() {
        
        String username = "testUser123";

        
        String token = jwtUtil.generateToken(username);

        
        byte[] keyBytes = Decoders.BASE64.decode(JwtUtil.SECRET);
        Key signingKey = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        
        assertEquals(username, claims.getSubject(), "Numele utilizatorului decodat nu se potrivește cu cel introdus");
        assertNotNull(claims.getIssuedAt(), "Data emiterii (IssuedAt) lipsește");
        assertNotNull(claims.getExpiration(), "Data expirării (Expiration) lipsește");
        
        
        assertTrue(claims.getExpiration().after(new Date()), "Token-ul generat este deja expirat");
    }
}