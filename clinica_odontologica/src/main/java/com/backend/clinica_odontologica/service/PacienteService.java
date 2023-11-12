package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.model.Paciente;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IDao<Paciente> dao;
    private ModelMapper modelMapper;

    public PacienteService(IDao<Paciente> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    public PacienteResponseDto registrarPaciente(PacienteRequestDto paciente) {
        LOGGER.info("PacienteRequestDto: " + JsonPrinter.toString(paciente));
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
        Paciente pacientePersistir =dao.registrar(pacienteEntidad);
        PacienteResponseDto pacienteResponseDto = modelMapper.map(pacientePersistir, PacienteResponseDto.class);
        LOGGER.info("PacienteResponseDto: " + JsonPrinter.toString(pacienteResponseDto));
        return pacienteResponseDto;
    }

    public List<PacienteResponseDto> listar() {
        List<PacienteResponseDto> pacientesResponseDto = new ArrayList<>();
        List<Paciente> pacientes = dao.listar();
        for (Paciente paciente : pacientes) {
            PacienteResponseDto pacienteResponseDto = modelMapper.map(paciente, PacienteResponseDto.class);
            pacientesResponseDto.add(pacienteResponseDto);
        }
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesResponseDto));
        return pacientesResponseDto;
    }

    public PacienteResponseDto buscarPorId(int id) {
        Paciente pacienteBuscado = dao.buscarPorId(id);
        PacienteResponseDto pacienteEncontrado = null;

        if(pacienteBuscado != null){
            pacienteEncontrado =  modelMapper.map(pacienteBuscado, PacienteResponseDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteEncontrado;
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        return dao.actualizar(paciente);
    }
    private void configureMapping(){
        modelMapper.typeMap(PacienteRequestDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteRequestDto::getDomicilioRequestDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteResponseDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacienteResponseDto::setDomicilioResponseDto));
    }

}
