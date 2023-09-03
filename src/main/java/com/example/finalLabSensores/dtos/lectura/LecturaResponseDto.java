package com.example.finalLabSensores.dtos.lectura;

import com.example.finalLabSensores.models.Sensor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturaResponseDto {
    private Long id;
    private Sensor sensor;
    private LocalDateTime readingDate;
    private BigDecimal measure;
}
