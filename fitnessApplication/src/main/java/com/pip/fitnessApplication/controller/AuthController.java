package com.pip.fitnessApplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pip.fitnessApplication.dto.SignupRequest;
import com.pip.fitnessApplication.dto.UserDTO;
import com.pip.fitnessApplication.services.auth.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.pip.fitnessApplication.dto.LoginRequest;
import com.pip.fitnessApplication.services.jwt.UserDetailsServiceImpl;
import com.pip.fitnessApplication.utils.JwtUtil;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth") // Toate link-urile de aici vor începe cu /api/auth
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        // 1. Verificăm dacă adresa de email e deja folosită
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists with this email!");
        }

        // 2. Creăm utilizatorul
        UserDTO createdUser = authService.createUser(signupRequest);
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created!");
        }

        // 3. Returnăm succes
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Încercăm să autentificăm utilizatorul (aici se face comparația de parole
            // ascunsă de noi)
            
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email sau parolă incorecte!");
        }

        // Dacă a ajuns aici, înseamnă că parola e corectă! Extragem datele și generăm
        // token-ul
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Trimitem înapoi către Angular un colet cu Token-ul și ID-ul utilizatorului
        if (optionalUser.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("jwt", jwt);
            response.put("userId", optionalUser.get().getId());
            response.put("role", optionalUser.get().getRole());
            response.put("name", optionalUser.get().getName());

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}