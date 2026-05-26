package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;
import com.pip.fitnessApplication.services.jwt.UserDetailsServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testLoadUserByUsername_Success() {
        String email = "test@fitness.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("hashed_password");

        Mockito.when(userRepository.findFirstByEmail(email)).thenReturn(Optional.of(user));

        String targetEmail = email;
        UserDetails userDetails = userDetailsService.loadUserByUsername(targetEmail);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals("hashed_password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound_ThrowsException() {
        String email = "notfound@fitness.com";
        Mockito.when(userRepository.findFirstByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(email);
        });
    }
}