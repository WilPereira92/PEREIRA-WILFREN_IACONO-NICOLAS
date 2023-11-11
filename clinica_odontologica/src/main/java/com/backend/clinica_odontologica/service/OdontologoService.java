package com.backend.clinica_odontologica.service;

import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao<Odontologo> dao;

    public OdontologoService(IDao<Odontologo> dao) {
        this.dao = dao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return dao.registrar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return dao.listar();
    }

    public Odontologo buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return dao.actualizar(odontologo);
    }
}
