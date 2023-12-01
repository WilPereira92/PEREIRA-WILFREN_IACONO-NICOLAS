package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.DomicilioRequestDto;
import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.backend.clinica_odontologica")
@RunWith(SpringRunner.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    public void deberiaRegistrarUnPacienteDeNombreJuanYRetornarElId() {
        PacienteRequestDto pacienteRequestDto = new PacienteRequestDto("Juan", "Perez", 123456789, LocalDate.of(2023, 12, 24),
                new DomicilioRequestDto("Gran Colombia", 109, "Las Acacias", "Caracas"));
        PacienteResponseDto pacienteResponseDto = pacienteService.registrarPaciente(pacienteRequestDto);
        assertNotNull(pacienteResponseDto.getId());
        assertEquals("Juan", pacienteResponseDto.getNombre());
    }

    @Test
    @Order(2)
    public void alIntentarEliminarUnPacienteConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException() {
        try {
            pacienteService.eliminarPaciente(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(3)
    public void deberiaDevolverUnaListaVaciaDePacientes() {
        List<PacienteResponseDto> pacienteResponseDtoList = pacienteService.listarPacientes();
        assertTrue(pacienteResponseDtoList.isEmpty());
    }
}