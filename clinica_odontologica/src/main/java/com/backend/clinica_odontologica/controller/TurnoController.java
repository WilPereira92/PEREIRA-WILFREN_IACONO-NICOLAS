package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @PostMapping("/registrar")
    public ResponseEntity<TurnoResponseDto> registrarTurno(@RequestBody @Valid TurnoRequestDto turnoRequestDto){
        return new ResponseEntity<>(turnoService.registrarTurno(turnoRequestDto), HttpStatus.CREATED);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<TurnoResponseDto>> listarTodos(){
        return new ResponseEntity<>(turnoService.listarTurnos(),HttpStatus.OK);
    }
}
