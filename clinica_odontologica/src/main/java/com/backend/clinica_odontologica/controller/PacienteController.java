package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.model.Paciente;
import com.backend.clinica_odontologica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<PacienteResponseDto> registrarPaciente(@RequestBody @Valid PacienteRequestDto paciente) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteResponseDto>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/buscarPorId")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@RequestParam int id) {
        return new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

}
