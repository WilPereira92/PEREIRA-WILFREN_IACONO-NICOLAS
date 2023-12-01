package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.backend.clinica_odontologica")
@RunWith(SpringRunner.class)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void deberiaRegistrarUnOdontologoDeNombreSyriusYRetornarElId() {
        OdontologoRequestDto odontologoRequestDto = new OdontologoRequestDto("123456A", "Syrius", "Alexander");
        OdontologoResponseDto odontologoResponseDto = odontologoService.registrarOdontologo(odontologoRequestDto);
        assertNotNull(odontologoResponseDto.getId());
        assertEquals("Syrius", odontologoResponseDto.getNombre());
    }

    @Test
    @Order(2)
    public void alIntentarEliminarUnOdontologoConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException() {
        try {
            odontologoService.eliminarOdontologo(1L);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(3)
    public void deberiaDevolverUnaListaVaciaDePacientes() {
        List<OdontologoResponseDto> odontologoResponseDtoList = odontologoService.listarOdontologos();
        assertTrue(odontologoResponseDtoList.isEmpty());
    }


}