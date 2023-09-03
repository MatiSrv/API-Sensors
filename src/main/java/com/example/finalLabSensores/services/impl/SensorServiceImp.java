package com.example.finalLabSensores.services.impl;

import com.example.finalLabSensores.dtos.sensor.SensorRequestDto;
import com.example.finalLabSensores.dtos.sensor.SensorResponseDto;
import com.example.finalLabSensores.entities.SensorEntity;
import com.example.finalLabSensores.models.Sensor;
import com.example.finalLabSensores.repositories.jpa.SensorJpaRepository;
import com.example.finalLabSensores.services.SensorService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class SensorServiceImp implements SensorService {
    @Autowired
    private SensorJpaRepository sensorJpaRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<SensorResponseDto> getAll() {
        List<SensorResponseDto> sensorResponseDtos = new ArrayList<>();
        List<SensorEntity> sensorEntities = sensorJpaRepository.findAll();

        for (SensorEntity sensor : sensorEntities){
            sensorResponseDtos.add(modelMapper.map(sensor, SensorResponseDto.class));
        }

        return  sensorResponseDtos;
    }

    @Override
    public SensorResponseDto createSensor(SensorRequestDto sensorRequestDto) {
        SensorEntity saved = sensorJpaRepository.save(modelMapper.map(sensorRequestDto, SensorEntity.class));
        return  modelMapper.map(saved, SensorResponseDto.class);
    }

    @Override
    public SensorResponseDto updateSensor(Long id,SensorRequestDto sensorRequestDto) {
        Optional<SensorEntity> sensorEntityOptional = sensorJpaRepository.findById(id);
        if(sensorEntityOptional.isEmpty()){
            throw new EntityNotFoundException("El id del sensor especificado no existe");
        }

        SensorEntity sensor =sensorEntityOptional.get();
        sensor.setDescription(sensorRequestDto.getDescription());
        sensor.setName(sensorRequestDto.getName());
        sensor.setUnitOfMeasurement(sensorRequestDto.getUnitOfMeasurement());

        sensorJpaRepository.save(sensor);

        return modelMapper.map(sensor, SensorResponseDto.class);
    }

    @Override
    public SensorResponseDto getByName(String name) {
        Optional<SensorEntity> sensorEntityOptional = sensorJpaRepository.findByName(name);

        if(sensorEntityOptional.isEmpty()){
            throw new EntityNotFoundException("El sensor con el nombre especificado no existe");
        }

        return modelMapper.map(sensorEntityOptional.get(), SensorResponseDto.class);
    }

    @Override
    public Sensor getById(Long id) {
        Optional<SensorEntity> sensorEntityOptional = sensorJpaRepository.findById(id);
        if (sensorEntityOptional.isEmpty()){
            throw  new EntityNotFoundException("El sensor no existe");
        }

        return modelMapper.map(sensorEntityOptional.get(), Sensor.class);
    }

    @Override
    public void deleteSensor(Long id) {
        Optional<SensorEntity> sensorOptional = sensorJpaRepository.findById(id);
        if (sensorOptional.isPresent()) {
            sensorJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El sensor con el ID especificado no existe");
        }
    }
}
