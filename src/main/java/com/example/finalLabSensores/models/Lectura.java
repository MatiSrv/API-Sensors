package com.example.finalLabSensores.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lectura {
private Long id;
private Sensor sensor;
private LocalDateTime readingDate;
private BigDecimal measure;
}
