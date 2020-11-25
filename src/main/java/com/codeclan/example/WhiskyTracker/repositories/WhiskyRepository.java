package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

  List<Whisky> findWhiskiesByYear(Integer year);

  List<Whisky> findWhiskiesByAgeAndDistilleryId(Integer age, Long id);

  List<Whisky> findWhiskiesByDistilleryId(Long id);

  List<Whisky> findWhiskiesByDistilleryRegion(String region);
}
