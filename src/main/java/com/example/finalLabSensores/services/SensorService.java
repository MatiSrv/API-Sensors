package com.example.finalLabSensores.services;

import com.example.finalLabSensores.dtos.sensor.SensorRequestDto;
import com.example.finalLabSensores.dtos.sensor.SensorResponseDto;
import com.example.finalLabSensores.models.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensorService {
  List< SensorResponseDto> getAll();
  SensorResponseDto createSensor(SensorRequestDto sensorRequestDto);
  SensorResponseDto updateSensor(Long id,SensorRequestDto sensorRequestDto);

    SensorResponseDto getByName(String name);

    Sensor  getById(Long id);

    void deleteSensor(Long id);
}
