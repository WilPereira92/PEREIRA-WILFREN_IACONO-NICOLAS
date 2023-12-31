package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.OdontologoResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import com.backend.clinica_odontologica.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologoRequestDto);

    List<OdontologoResponseDto> listarOdontologos();

    OdontologoResponseDto buscarPorId(Long id);

    OdontologoResponseDto actualizarOdontologo(OdontologoResquestUpdateDto odontologo) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    Odontologo entidadOdontologo(Long id);
}
