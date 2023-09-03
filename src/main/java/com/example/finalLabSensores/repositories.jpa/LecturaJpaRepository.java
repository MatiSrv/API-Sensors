package com.example.finalLabSensores.repositories.jpa;

import com.example.finalLabSensores.entities.LecturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LecturaJpaRepository extends JpaRepository<LecturaEntity, Long> {
    @Query("SELECT l FROM LecturaEntity l WHERE l.readingDate BETWEEN :fromDate AND :toDate")
    List<LecturaEntity> findByDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
