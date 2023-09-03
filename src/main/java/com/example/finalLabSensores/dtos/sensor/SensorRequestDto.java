package com.example.finalLabSensores.dtos.sensor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequestDto {
    private String name;
    private String description;
    private String unitOfMeasurement;
}
