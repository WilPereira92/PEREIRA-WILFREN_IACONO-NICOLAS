package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.TurnoRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.TurnoRequestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.DomicilioResponseDto;
import com.backend.clinica_odontologica.dto.salida.OdontologoResponseDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.dto.salida.TurnoResponseDto;
import com.backend.clinica_odontologica.model.Domicilio;
import com.backend.clinica_odontologica.model.Odontologo;
import com.backend.clinica_odontologica.model.Paciente;
import com.backend.clinica_odontologica.model.Turno;
import com.backend.clinica_odontologica.repository.TurnoRepository;
import com.backend.clinica_odontologica.service.IOdontologoService;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.service.ITurnoService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    private ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto){
        PacienteResponseDto pacienteFindId = pacienteService.buscarPorId(turnoRequestDto.getIdPaciente());
        Paciente pacienteEntidad = modelMapper.map(pacienteFindId, Paciente.class);
        LOGGER.info("Paciente del turno: {}", JsonPrinter.toString(pacienteEntidad));
        OdontologoResponseDto odontologoFindId = odontologoService.buscarPorId(turnoRequestDto.getIdOdontologo());
        Odontologo odontologoEntidad = modelMapper.map(odontologoFindId, Odontologo.class);
        LOGGER.info("Odontologo del turno: {}", JsonPrinter.toString(odontologoEntidad));
        Turno turnoEntidad = new Turno();
        turnoEntidad.setFechaYHoraTurno(turnoRequestDto.getFechaYHoraTurno());
        turnoEntidad.setOdontologo(odontologoEntidad);
        turnoEntidad.setPaciente(pacienteEntidad);
        LOGGER.info("Turno a persistir: {}", JsonPrinter.toString(turnoEntidad));
        Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
        TurnoResponseDto turnoResponseDto = new TurnoResponseDto();
        turnoResponseDto.setOdontologoResponseDto(odontologoFindId);
        turnoResponseDto.setPacienteResponseDto(pacienteFindId);
        turnoResponseDto.setFechaYHoraTurno(turnoAPersistir.getFechaYHoraTurno());
        turnoResponseDto.setId(turnoAPersistir.getId());
        LOGGER.info("Se registró el turno: {}", JsonPrinter.toString(turnoResponseDto));
        return turnoResponseDto;
    }
    public List<TurnoResponseDto> listarTurnos(){
        List<TurnoResponseDto> turnosResponseDto = new ArrayList<>();
        List<Turno> turnoList = turnoRepository.findAll();
        for (Turno turno : turnoList) {
            PacienteResponseDto pacienteResponseDto = modelMapper.map(turno.getPaciente(), PacienteResponseDto.class);
            OdontologoResponseDto odontologoResponseDto = modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class);
            TurnoResponseDto turnoResponseDto = new TurnoResponseDto(turno.getId(), turno.getFechaYHoraTurno(), pacienteResponseDto, odontologoResponseDto);
            turnosResponseDto.add(turnoResponseDto);

        }
        LOGGER.info("Se listaron los siguientes turnos: " + JsonPrinter.toString(turnosResponseDto));

        return turnosResponseDto;
    }
    public void configureMapping(){
        modelMapper.typeMap(OdontologoResponseDto.class, Odontologo.class);
        modelMapper.typeMap(PacienteResponseDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteResponseDto::getDomicilioResponseDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteResponseDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacienteResponseDto::setDomicilioResponseDto));
        modelMapper.typeMap(Odontologo.class, OdontologoResponseDto.class);
        modelMapper.typeMap(Turno.class, TurnoResponseDto.class);
    }


}
