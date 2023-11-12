package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.dto.entrada.OdontologoRequestDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.model.Odontologo;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IDao<Odontologo> dao;
    private ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo) {
        LOGGER.info("OdontólogRequestDto: {}" + JsonPrinter.toString(odontologo));
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoPersistido = dao.registrar(odontologoEntidad);
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologoPersistido, OdontologoResponseDto.class);
        LOGGER.info("Se registró el odontólogo: " + JsonPrinter.toString(odontologoResponseDto));
        return odontologoResponseDto;
    }

    public List<OdontologoResponseDto> listarOdontologos() {
        List<OdontologoResponseDto> odontologosResponseDto = new ArrayList<>();
        List<Odontologo> odontologos = dao.listar();
        for (Odontologo odontologo : odontologos) {
            OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
            odontologosResponseDto.add(odontologoResponseDto);
        }
        LOGGER.info("Se listaron los siguientes odontólogos: " + JsonPrinter.toString(odontologosResponseDto));
        return odontologosResponseDto;
    }

    public OdontologoResponseDto buscarPorId(int id) {
        Odontologo odontologo = dao.buscarPorId(id);
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        LOGGER.info("Se encontró el odontólogo: " + JsonPrinter.toString(odontologoResponseDto));
        return odontologoResponseDto;
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return dao.actualizar(odontologo);
    }
    public void configureMapping(){
        modelMapper.typeMap(OdontologoRequestDto.class, Odontologo.class);
        modelMapper.typeMap(Odontologo.class, OdontologoResponseDto.class);
    }
}
