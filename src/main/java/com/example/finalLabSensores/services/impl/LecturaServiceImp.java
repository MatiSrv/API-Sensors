package com.example.finalLabSensores.services.impl;

import com.example.finalLabSensores.dtos.lectura.LecturaRequestDto;
import com.example.finalLabSensores.dtos.lectura.LecturaResponseDto;
import com.example.finalLabSensores.dtos.sensor.SensorResponseDto;
import com.example.finalLabSensores.entities.LecturaEntity;
import com.example.finalLabSensores.entities.SensorEntity;
import com.example.finalLabSensores.models.Lectura;
import com.example.finalLabSensores.models.Sensor;
import com.example.finalLabSensores.repositories.jpa.LecturaJpaRepository;
import com.example.finalLabSensores.services.LecturaService;
import com.example.finalLabSensores.services.SensorService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LecturaServiceImp implements LecturaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired 
    private LecturaJpaRepository lecturaJpaRepository;
    @Autowired
    private SensorService sensorService;

    @Override
    public List<LecturaResponseDto> getAll() {
        List<LecturaResponseDto> lecturaResponseDtos = new ArrayList<>();
        List<LecturaEntity> lecturaEntities = lecturaJpaRepository.findAll();

        for (LecturaEntity lectura : lecturaEntities){
            lecturaResponseDtos.add(modelMapper.map(lectura, LecturaResponseDto.class));
        }

        return  lecturaResponseDtos;
    }

    @Override
    public List<LecturaResponseDto> getBetween(LocalDateTime from, LocalDateTime to) {
        List<LecturaEntity> lecturaEntities = lecturaJpaRepository.findByDateBetween(from,to);
        List<LecturaResponseDto> lecturaResponseDtos = new ArrayList<>() ;
       for(LecturaEntity lectura : lecturaEntities){
           lecturaResponseDtos.add(modelMapper.map(lectura, LecturaResponseDto.class));
       }

       return lecturaResponseDtos;
    }

    @Override
    public LecturaResponseDto getByDates(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public LecturaResponseDto createLectura(LecturaRequestDto lecturaRequestDto) {
        LecturaEntity savedLectura = lecturaJpaRepository.save(modelMapper.map(lecturaRequestDto, LecturaEntity.class));
        return modelMapper.map(savedLectura, LecturaResponseDto.class);
    }

    @Override
    public LecturaResponseDto updateLectura(Long id, LecturaRequestDto lecturaRequestDto) {
        Optional<LecturaEntity> lecturaEntityOptional = lecturaJpaRepository.findById(id);
        if(lecturaEntityOptional.isEmpty()){
            throw new EntityNotFoundException("La lectura no existe");
        }

        LecturaEntity lectura = lecturaEntityOptional.get();
        lectura.setMeasure(lecturaRequestDto.getMeasure());

        Sensor sensor = sensorService.getById(lecturaRequestDto.getIdSensor());
        lectura.setSensor(modelMapper.map(sensor, SensorEntity.class));

        lectura.setReadingDate(lecturaRequestDto.getReadingDate());

        lecturaJpaRepository.save(lectura);

        return modelMapper.map(lectura, LecturaResponseDto.class);
    }

    @Override
    public void deleteLectura(Long id) {
        Optional<LecturaEntity> lecturaEntityOptional = lecturaJpaRepository.findById(id);
        if (lecturaEntityOptional.isPresent()) {
            lecturaJpaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("La lectura con el ID especificado no existe");
        }
    }
}
