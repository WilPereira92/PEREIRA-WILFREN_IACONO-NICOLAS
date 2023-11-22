package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.OdontologoResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;

import java.util.List;

public interface IOdontologoService {
    OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologoRequestDto);
    List<OdontologoResponseDto> listarOdontologos();
    OdontologoResponseDto buscarPorId(Long id);
    OdontologoResponseDto actualizarOdontologo(OdontologoResquestUpdateDto odontologo);
    void eliminarOdontologo(Long id);
}
