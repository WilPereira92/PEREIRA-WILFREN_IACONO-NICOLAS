package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @PostMapping("/registrar")
    public ResponseEntity<TurnoResponseDto> registrarTurno(@RequestBody @Valid TurnoRequestDto turnoRequestDto){
        return new ResponseEntity<>(turnoService.registrarTurno(turnoRequestDto), HttpStatus.CREATED);
    }
}
