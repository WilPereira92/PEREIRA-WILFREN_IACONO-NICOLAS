package com.backend.clinica_odontologica.controller;

import com.backend.clinica_odontologica.model.Paciente;
import com.backend.clinica_odontologica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.registrarPaciente(paciente);
    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes() {
        return pacienteService.listar();
    }

    @GetMapping("/buscarPorId")
    public Paciente buscarPorId(@RequestParam int id) {
        return pacienteService.buscarPorId(id);
    }

    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

}
