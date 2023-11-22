package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.PacienteResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;

import java.util.List;

public interface IPacienteService {
    PacienteResponseDto registrarPaciente(PacienteRequestDto paciente);
    List<PacienteResponseDto> listarPacientes();
    PacienteResponseDto buscarPorId(Long id);
    PacienteResponseDto actualizarPaciente(PacienteResquestUpdateDto paciente);
    void eliminarPaciente(Long id);
}
