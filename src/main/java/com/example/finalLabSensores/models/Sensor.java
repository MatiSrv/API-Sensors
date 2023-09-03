package com.example.finalLabSensores.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    private Long id;
    private String name;
    private String description;
    private String unitOfMeasurement;
}
