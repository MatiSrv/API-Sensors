package com.example.finalLabSensores.services;

import com.example.finalLabSensores.dtos.lectura.LecturaRequestDto;
import com.example.finalLabSensores.dtos.lectura.LecturaResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface LecturaService {
    List<LecturaResponseDto> getAll();
    //Hacer Get By Dates
    List<LecturaResponseDto> getBetween(LocalDateTime from, LocalDateTime to);
    LecturaResponseDto getByDates(LocalDateTime from, LocalDateTime to);
    LecturaResponseDto createLectura(LecturaRequestDto lecturaRequestDto);
    LecturaResponseDto updateLectura(Long id,LecturaRequestDto lecturaRequestDto);

    void deleteLectura(Long id);


}
