package com.backend.clinica_odontologica.service.impl;


import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.OdontologoResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import com.backend.clinica_odontologica.model.Odontologo;
import com.backend.clinica_odontologica.repository.OdontologoRepository;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    private ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo) {
        LOGGER.info("OdontólogRequestDto: {}" + JsonPrinter.toString(odontologo));
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoPersistido = odontologoRepository.save(odontologoEntidad);
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologoPersistido, OdontologoResponseDto.class);
        LOGGER.info("Se registró el odontólogo: " + JsonPrinter.toString(odontologoResponseDto));
        return odontologoResponseDto;
    }

    @Override
    public List<OdontologoResponseDto> listarOdontologos() {
        List<OdontologoResponseDto> odontologosResponseDto = new ArrayList<>();
        List<Odontologo> odontologos = odontologoRepository.findAll();
        for (Odontologo odontologo : odontologos) {
            OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
            odontologosResponseDto.add(odontologoResponseDto);
        }
        LOGGER.info("Se listaron los siguientes odontólogos: " + JsonPrinter.toString(odontologosResponseDto));
        return odontologosResponseDto;
    }

    @Override
    public OdontologoResponseDto buscarPorId(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        OdontologoResponseDto odontologoResponseDto = null;
        if (odontologo != null) {
            odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
            LOGGER.info("Odontólogo encontrado: {}", JsonPrinter.toString(odontologoResponseDto));
        } else LOGGER.error("El id no se encuentra registrado en la Base de datos");

        return odontologoResponseDto;
    }

    @Override
    public OdontologoResponseDto actualizarOdontologo(OdontologoResquestUpdateDto odontologo) throws ResourceNotFoundException {
        LOGGER.info("Odontólogo a actualizarRequestUpdateDto: {}", JsonPrinter.toString(odontologo));
        Odontologo odontologoUpdate = modelMapper.map(odontologo, Odontologo.class);
        LOGGER.info("Odontólogo a actualizar: {}", JsonPrinter.toString(odontologoUpdate));
        Odontologo odontologoModified = odontologoRepository.findById(odontologoUpdate.getId()).orElse(null);
        OdontologoResponseDto odontologoResponseDto = null;
        if (odontologoModified != null) {
            odontologoModified = odontologoUpdate;
            odontologoRepository.save(odontologoModified);
            odontologoResponseDto = modelMapper.map(odontologoModified, OdontologoResponseDto.class);
            LOGGER.warn("Odontólogo actualizado: {}", JsonPrinter.toString(odontologoModified));
        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque el mismo no se encuentra regitrado en la base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el odontólogo porque no se encuentra en la base de datos");
        }
        return odontologoResponseDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (odontologoRepository.findById(id).orElse(null) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se eliminó el odontólogo con el id " + id);
        } else {
            LOGGER.error("No se ha encontrado el odontólogo con el id: " + id);
            throw new ResourceNotFoundException("No se ha encontrado el odontólogo con id: " + id);
        }
    }

    @Override
    public Odontologo entidadOdontologo(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    public void configureMapping() {
        modelMapper.typeMap(OdontologoRequestDto.class, Odontologo.class);
        modelMapper.typeMap(Odontologo.class, OdontologoResponseDto.class);
        modelMapper.typeMap(OdontologoResquestUpdateDto.class, Odontologo.class);
    }
}
