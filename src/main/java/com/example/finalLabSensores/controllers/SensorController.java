package com.example.finalLabSensores.controllers;

import com.example.finalLabSensores.dtos.sensor.SensorRequestDto;
import com.example.finalLabSensores.dtos.sensor.SensorResponseDto;
import com.example.finalLabSensores.services.SensorService;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping("/get-all")
    public ResponseEntity<List<SensorResponseDto>> getAll(){
        return ResponseEntity.ok(sensorService.getAll());
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<SensorResponseDto> getByName(@PathVariable String name){
        return ResponseEntity.ok(sensorService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<SensorResponseDto> createSensor(@RequestBody SensorRequestDto sensorRequestDto){
        return ResponseEntity.ok(sensorService.createSensor(sensorRequestDto));
    }

    @PutMapping("/update-sensor/{id}")
    public ResponseEntity<SensorResponseDto> updateSensor(@PathVariable Long id,@RequestBody SensorRequestDto sensorRequestDto){
        return  ResponseEntity.ok(sensorService.updateSensor(id,sensorRequestDto));
    }

    @DeleteMapping("delete-sensor/{id}")
    public ResponseEntity<String> deleteSensor(@PathVariable Long id){
        sensorService.deleteSensor(id);
        return ResponseEntity.ok("Sensor borrado exitosamente");
    }

}
