package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private IDao<Paciente> dao;

    public PacienteService(IDao<Paciente> dao) {
        this.dao = dao;
    }

    public Paciente registrarPaciente(Paciente paciente) {
        return dao.registrar(paciente);
    }

    public List<Paciente> listar() {
        return dao.listar();
    }

    public Paciente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        return dao.actualizar(paciente);
    }


}
