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
        // Inițializăm utilitarul (fiind o clasă simplă, nu avem nevoie de Mockito)
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken_ShouldReturnValidTokenString() {
        // 1. Arrange
        String username = "alex_fitness_master";

        // 2. Act
        String token = jwtUtil.generateToken(username);

        // 3. Assert
        assertNotNull(token, "Token-ul generat nu ar trebui să fie null");
        assertFalse(token.isEmpty(), "Token-ul nu ar trebui să fie un string gol");
        
        // Un token JWT este compus din 3 părți separate prin punct (Header.Payload.Signature)
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "Un token JWT valid trebuie să aibă exact 3 secțiuni separate prin punct");
    }

    @Test
    public void testGenerateToken_ShouldContainCorrectSubjectAndExpiration() {
        // 1. Arrange
        String username = "testUser123";

        // 2. Act
        String token = jwtUtil.generateToken(username);

        // Pentru validare, decodificăm manual token-ul folosind aceeași cheie secretă
        byte[] keyBytes = Decoders.BASE64.decode(JwtUtil.SECRET);
        Key signingKey = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 3. Assert (Validăm datele din interiorul token-ului)
        assertEquals(username, claims.getSubject(), "Numele utilizatorului decodat nu se potrivește cu cel introdus");
        assertNotNull(claims.getIssuedAt(), "Data emiterii (IssuedAt) lipsește");
        assertNotNull(claims.getExpiration(), "Data expirării (Expiration) lipsește");
        
        // Verificăm dacă data expirării este în viitor
        assertTrue(claims.getExpiration().after(new Date()), "Token-ul generat este deja expirat");
    }
}