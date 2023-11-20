package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.PacienteResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
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

    @GetMapping("/buscarPorId")
    public ResponseEntity<PacienteResponseDto> buscarPorId(@RequestParam Long id) {
        return new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteResponseDto>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }


    @PutMapping("/actualizar")
    public PacienteResponseDto actualizarPaciente(@RequestBody PacienteResquestUpdateDto paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id) {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.OK);
    }

}
