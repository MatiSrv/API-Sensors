package com.example.finalLabSensores.repositories.jpa;

import com.example.finalLabSensores.entities.SensorEntity;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorJpaRepository extends JpaRepository<SensorEntity, Long> {
    Optional<SensorEntity> findByName(String name);
}
