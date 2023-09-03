package com.example.finalLabSensores.LecturaControllerTest;

import com.example.finalLabSensores.controllers.LecturaController;
import com.example.finalLabSensores.dtos.lectura.LecturaRequestDto;
import com.example.finalLabSensores.dtos.lectura.LecturaResponseDto;
import com.example.finalLabSensores.models.Sensor;
import com.example.finalLabSensores.services.LecturaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;



import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(LecturaController.class)
public class LecturaControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LecturaService lecturaService;

    @Test
    public void testCreateLectura() throws Exception {
        LecturaRequestDto lecturaRequestDto = new LecturaRequestDto();
        lecturaRequestDto.setIdSensor(1L);
        lecturaRequestDto.setReadingDate(LocalDateTime.now());
        lecturaRequestDto.setMeasure(BigDecimal.valueOf(10.5));

        LecturaResponseDto lecturaResponseDto = new LecturaResponseDto();
        lecturaResponseDto.setId(1L);
        lecturaResponseDto.setSensor(new Sensor(1L, "Sensor 1", "Descripción del sensor", "C°"));
        lecturaResponseDto.setReadingDate(LocalDateTime.now());
        lecturaResponseDto.setMeasure(BigDecimal.valueOf(10.5));


        given(lecturaService.createLectura(any(LecturaRequestDto.class))).willReturn(lecturaResponseDto);

        mvc.perform(post("/lecturas/create-lectura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lecturaRequestDto)))
                .andExpect(status().isOk());


    }

    @Test
   public  void testDeleteLectura() throws Exception {
        // Test data
        Long lecturaId = 1L;

        // When
        mvc.perform(delete("/lecturas/delete/{id}", lecturaId))
                .andExpect(status().isOk())
                .andExpect(content().string("Lectura borrada exitosamente"));

    }

}
