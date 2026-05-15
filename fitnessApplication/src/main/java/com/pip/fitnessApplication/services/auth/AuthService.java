package com.pip.fitnessApplication.services.auth;

import com.pip.fitnessApplication.dto.SignupRequest;
import com.pip.fitnessApplication.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}