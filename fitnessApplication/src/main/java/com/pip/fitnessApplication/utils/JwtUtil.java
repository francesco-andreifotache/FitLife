package com.pip.fitnessApplication.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilitar component Spring responsabil cu gestionarea token-urilor JWT (JSON Web Tokens).
 * <p>
 * Oferă funcționalități pentru crearea, configurarea claim-urilor, setarea duratei de valabilitate 
 * și semnarea digitală criptografică a token-urilor folosite în procesul de autentificare.
 * </p>
 *
 * @author Alex
 * @version 1.0
 */
@Component
public class JwtUtil {

    /** * Cheia secretă în format Base64 utilizată pentru semnarea digitală a token-urilor JWT.
     * <p><b>Atenție:</b> În medii de producție reală, această cheie trebuie stocată în variabile de mediu securizate, nu direct în cod.</p>
     */
    public static final String SECRET = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";

    /**
     * Generează un token JWT valid pentru un anumit nume de utilizator (username).
     *
     * @param userName Numele utilizatorului pentru care se emite token-ul.
     * @return Un șir de caractere (String) ce reprezintă token-ul JWT compactat și semnat.
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    /**
     * Construiește token-ul JWT folosind builder-ul din librăria jjwt.
     * <p>
     * Setează claim-urile personalizate, subiectul (username), timestamp-ul emiterii 
     * și o dată de expirare fixată la 24 de ore din momentul generării.
     * </p>
     *
     * @param claims Map-ul cu informații adiționale ce vor fi incluse în payload.
     * @param userName Subiectul principal al token-ului (Subject).
     * @return Token-ul formatat și criptat ca string.
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Token-ul e valabil 24 ore
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Decodează cheia secretă din formatul Base64 și generează o cheie criptografică HMAC validă.
     *
     * @return Obiectul {@link Key} folosit pentru algoritmul de semnare HS256.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}