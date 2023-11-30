package com.backend.clinica_odontologica.service.impl;

import com.backend.clinica_odontologica.dto.entrada.PacienteRequestDto;
import com.backend.clinica_odontologica.dto.entrada.modificacion.PacienteResquestUpdateDto;
import com.backend.clinica_odontologica.dto.salida.PacienteResponseDto;
import com.backend.clinica_odontologica.exception.ResourceNotFoundException;
import com.backend.clinica_odontologica.model.Paciente;
import com.backend.clinica_odontologica.repository.PacienteRepository;
import com.backend.clinica_odontologica.service.IPacienteService;
import com.backend.clinica_odontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;
    private ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteResponseDto registrarPaciente(PacienteRequestDto paciente) {
        LOGGER.info("PacienteRequestDto: " + JsonPrinter.toString(paciente));
        LOGGER.info("DomicilioRequestDto: " + JsonPrinter.toString(paciente.getDomicilioRequestDto()));
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
        Paciente pacientePersistir = pacienteRepository.save(pacienteEntidad);
        PacienteResponseDto pacienteResponseDto = modelMapper.map(pacientePersistir, PacienteResponseDto.class);
        LOGGER.info("PacienteResponseDto: " + JsonPrinter.toString(pacienteResponseDto));
        return pacienteResponseDto;
    }

    @Override
    public List<PacienteResponseDto> listarPacientes() {
        List<PacienteResponseDto> pacientesResponseDto = new ArrayList<>();
        List<Paciente> pacientes = pacienteRepository.findAll();
        for (Paciente paciente : pacientes) {
            PacienteResponseDto pacienteResponseDto = modelMapper.map(paciente, PacienteResponseDto.class);
            pacientesResponseDto.add(pacienteResponseDto);
        }
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesResponseDto));
        return pacientesResponseDto;
    }

    @Override
    public PacienteResponseDto buscarPorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteResponseDto pacienteEncontrado = null;

        if (pacienteBuscado != null) {
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteResponseDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteEncontrado;
    }

    @Override
    public PacienteResponseDto actualizarPaciente(PacienteResquestUpdateDto paciente) throws ResourceNotFoundException {
        LOGGER.info("Paciente PacienteRequestUpdateDto: {}", JsonPrinter.toString(paciente));
        Paciente pacienteRecibido = modelMapper.map(paciente, Paciente.class);
        LOGGER.info("Paciente a actualizar: {}", JsonPrinter.toString(pacienteRecibido));
        Paciente pacienteAActualizar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
        PacienteResponseDto pacienteResponseDto = null;

        if (pacienteAActualizar != null) {
            pacienteAActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteAActualizar);

            pacienteResponseDto = modelMapper.map(pacienteAActualizar, PacienteResponseDto.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteRecibido));

        } else {
            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el paciente porque no se encuentra en la base de datos");
        }
        return pacienteResponseDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.findById(id).orElse(null) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: " + id);
        } else {
            LOGGER.error("No se ha encontrado el paciente con id: " + id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente con id: " + id);
        }
    }

    @Override
    public Paciente entidadPaciente(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteRequestDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteRequestDto::getDomicilioRequestDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteResponseDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacienteResponseDto::setDomicilioResponseDto));
        modelMapper.typeMap(PacienteResquestUpdateDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteResquestUpdateDto::getDomicilioRequestDto, Paciente::setDomicilio));
    }

}
