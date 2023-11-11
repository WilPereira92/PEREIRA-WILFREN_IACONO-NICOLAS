package com.backend.clinica_odontologica.dao;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);

    List<T> listar();

    T buscarPorId(int id);

    T actualizar(T t);
}
