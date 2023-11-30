package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.TurnoRequestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.exception.BadRequestException;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto) throws BadRequestException;

    List<TurnoResponseDto> listarTurnos();

    TurnoResponseDto buscarPorId(Long id);

    TurnoResponseDto actualizarTurno(TurnoRequestUpdateDto turno) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
