package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;

import java.util.List;

public interface ITurnoService {
    TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto);
    List<TurnoResponseDto> listarTurnos();
}
