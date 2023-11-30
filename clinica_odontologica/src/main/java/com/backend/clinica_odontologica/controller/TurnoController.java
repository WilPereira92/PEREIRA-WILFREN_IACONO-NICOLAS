package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.TurnoRequestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.exception.BadRequestException;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import com.backend.clinica_odontologica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<TurnoResponseDto> registrarTurno(@RequestBody @Valid TurnoRequestDto turnoRequestDto) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurnoResponseDto>> listarTodos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public TurnoResponseDto actualizarTurno(@RequestBody @Valid TurnoRequestUpdateDto turno) throws ResourceNotFoundException {
        return turnoService.actualizarTurno(turno);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }
    @GetMapping("/buscarPorId")
    public ResponseEntity<TurnoResponseDto> buscarPorId(@RequestParam Long id){
        return new ResponseEntity<>(turnoService.buscarPorId(id), HttpStatus.OK);
    }
}
