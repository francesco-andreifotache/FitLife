package com.pip.fitnessApplication.repository;

import com.pip.fitnessApplication.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // face legatura cu baza de date
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
