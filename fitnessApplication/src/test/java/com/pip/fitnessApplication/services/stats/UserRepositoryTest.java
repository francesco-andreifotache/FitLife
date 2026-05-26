package com.pip.fitnessApplication.services.stats;

import com.pip.fitnessApplication.entity.User;
import com.pip.fitnessApplication.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindFirstByEmail_Found() {
        User user = new User();
        user.setName("Alex");
        user.setEmail("alex@fitness.com");

        Mockito.when(userRepository.findFirstByEmail("alex@fitness.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userRepository.findFirstByEmail("alex@fitness.com");

        assertTrue(foundUser.isPresent());
        assertEquals("Alex", foundUser.get().getName());
    }

    @Test
    public void testFindFirstByEmail_NotFound() {
        Mockito.when(userRepository.findFirstByEmail("nonexistent@fitness.com")).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepository.findFirstByEmail("nonexistent@fitness.com");

        assertTrue(foundUser.isEmpty());
    }
}