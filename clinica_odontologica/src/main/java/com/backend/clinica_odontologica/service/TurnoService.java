package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.TurnoRequestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.model.Turno;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TurnoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }
    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto){
        LOGGER.info("TurnoRequestDto:{}", JsonPrinter.toString(turnoRequestDto));
        LOGGER.info("PacienteRequestDto: " + JsonPrinter.toString(turnoRequestDto.getPacienteRequestDto()));
        LOGGER.info("OdontologoRequestDto: " + JsonPrinter.toString(turnoRequestDto.getOdontologoRequestDto()));
        Turno turnoEntidad = modelMapper.map(turnoRequestDto, Turno.class);
        Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
        TurnoResponseDto turnoResponseDto = modelMapper.map(turnoAPersistir, TurnoResponseDto.class);
        LOGGER.info("Se registró el turno: {}", JsonPrinter.toString(turnoResponseDto));
        return turnoResponseDto;
    }


    public void configureMapping(){
        modelMapper.typeMap(TurnoRequestDto.class, Turno.class)
                .addMappings(modelMapper -> modelMapper.map(TurnoRequestDto::getOdontologoRequestDto, Turno::setOdontologo))
                .addMappings(modelMapper -> modelMapper.map(TurnoRequestDto::getPacienteRequestDto, Turno::setPaciente));
        modelMapper.typeMap(Turno.class, TurnoResponseDto.class)
                .addMappings(modelMapper -> modelMapper.map(Turno::getOdontologo, TurnoResponseDto::setOdontologoResponseDto))
                .addMappings(modelMapper -> modelMapper.map(Turno::getPaciente, TurnoResponseDto::setPacienteResponseDto));
        modelMapper.typeMap(TurnoRequestUpdateDto.class, Turno.class)
                .addMappings(modelMapper -> modelMapper.map(TurnoRequestUpdateDto::getOdontologoResquestUpdateDto, Turno::setOdontologo))
                .addMappings(modelMapper -> modelMapper.map(TurnoRequestUpdateDto::getPacienteResquestUpdateDto, Turno::setPaciente));

    }
}
