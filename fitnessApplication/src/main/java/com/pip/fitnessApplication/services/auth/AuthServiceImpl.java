package com.pip.fitnessApplication.services.auth;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pip.fitnessApplication.dto.SignupRequest;
import com.pip.fitnessApplication.dto.UserDTO;
import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        //se ascunde parola
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole("USER"); 

        User createdUser = userRepository.save(user);
        return createdUser.getUserDTO();
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}