package com.example.finalLabSensores.controllers;

import com.example.finalLabSensores.dtos.lectura.LecturaRequestDto;
import com.example.finalLabSensores.dtos.lectura.LecturaResponseDto;
import com.example.finalLabSensores.services.LecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProviderExposingInterceptor;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("lecturas")
public class LecturaController {
    @Autowired
    private LecturaService lecturaService;

    @GetMapping("/get-all")
    public ResponseEntity<List<LecturaResponseDto>>getAll(){
        return  ResponseEntity.ok(lecturaService.getAll());
    }

    @PostMapping("/create-lectura")
    public ResponseEntity<LecturaResponseDto> createLectura(@RequestBody LecturaRequestDto lecturaRequestDto){
        return  ResponseEntity.ok(lecturaService.createLectura(lecturaRequestDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LecturaResponseDto> updateLectura(@PathVariable Long id, @RequestBody LecturaRequestDto lecturaRequestDto){
        return ResponseEntity.ok(lecturaService.updateLectura(id, lecturaRequestDto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteRent(@PathVariable Long id){
        lecturaService.deleteLectura(id);
        return ResponseEntity.ok("Renta borrada exitosamente");
    }

    @GetMapping("/get-between")
    public ResponseEntity<List<LecturaResponseDto>> getLecturasBetween(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fromDate,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime toDate) {
        List<LecturaResponseDto> lecturas = lecturaService.getBetween(fromDate, toDate);
        return ResponseEntity.ok(lecturas);
    }
}
