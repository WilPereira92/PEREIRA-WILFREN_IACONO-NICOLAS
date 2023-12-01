package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioRequestDto;
import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.exception.BadRequestException;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import com.backend.clinica_odontologica.model.Odontologo;
import com.backend.clinica_odontologica.model.Paciente;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.backend.clinica_odontologica")
@RunWith(SpringRunner.class)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    public void deberiaArrojarUnaExcepcionPorTratarDeRegistrarUnTurnoSinPacienteYOdontologoRegistrado() throws BadRequestException {
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto(LocalDateTime.of(2024, 01, 01, 0, 0, 0), 1L, 2L);
        try {
            turnoService.registrarTurno(turnoRequestDto);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(BadRequestException.class, () -> turnoService.registrarTurno(turnoRequestDto));
    }

    @Test
    @Order(2)
    public void deberiaArrojarUnaListaDeTurnosVacia(){
        List<TurnoResponseDto> turnoResponseDtoList = turnoService.listarTurnos();
        assertTrue(turnoResponseDtoList.isEmpty());
    }
    @Test
    @Order(3)
    public void alIntentarEliminarUnTurnoConIdNoRegistradoDeberiaArrojarUnaExcepcion(){
        try {
            turnoService.eliminarTurno(2L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(2L));
    }
}