package com.example.finalLabSensores.entities;

import com.example.finalLabSensores.models.Sensor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity sensor;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime readingDate;

    @Column
    private BigDecimal measure;
}
