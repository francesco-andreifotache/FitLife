package com.pip.fitnessApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.pip.fitnessApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Această metodă magică va fi generată automat de Spring Boot
    // Ne ajută să găsim un user după email când dă Login
    Optional<User> findFirstByEmail(String email);

}