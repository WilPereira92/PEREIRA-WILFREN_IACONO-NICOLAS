package com.backend.clinica_odontologica.dao.impl;

import com.backend.clinica_odontologica.dao.H2Connection;
import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.model.Domicilio;
import com.backend.clinica_odontologica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {
    private static Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
    private DomicilioDaoH2 domicilioDaoH2;

    public PacienteDaoH2() {
    }

    public PacienteDaoH2(DomicilioDaoH2 domicilioDaoH2) {
        this.domicilioDaoH2 = domicilioDaoH2;
    }

    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = null;
        Paciente pacienteRegistrado = null;
        String insert = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, ID_DOMICILIO) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            domicilioDaoH2 = new DomicilioDaoH2();
            Domicilio domicilioRegistrado = domicilioDaoH2.registrar(paciente.getDomicilio());
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, domicilioRegistrado.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                pacienteRegistrado = new Paciente(resultSet.getInt("ID"), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioRegistrado);
            }
            LOGGER.info("Se logró registrar el paciente " + pacienteRegistrado);
            connection.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return pacienteRegistrado;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection = null;
        String select = "SELECT * FROM PACIENTES";
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Paciente paciente = crearObjetoPaciente(resultSet);
                pacientes.add(paciente);
            }
            LOGGER.info("Se logró listar los siguientes pacientes: " + pacientes);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente pacienteEncontrado = null;
        String select = "SELECT * FROM PACIENTES WHERE ID = ?";
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pacienteEncontrado = crearObjetoPaciente(resultSet);
            }
            if (pacienteEncontrado != null)
                LOGGER.info("Se encontró el paciente con el id " + pacienteEncontrado.getId() + " - " + pacienteEncontrado);
            else
                LOGGER.info("El paciente con id: " + pacienteEncontrado.getId() + " no se encuentra registrado en la BDD");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacienteEncontrado;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;
        Paciente pacienteActualizado = null;
        String update = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA_INGRESO = ?, ID_DOMICILIO = ? WHERE ID = ?";
        String select = "SELECT * FROM PACIENTES WHERE ID = ?";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());
            preparedStatement.setInt(6, paciente.getId());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, paciente.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pacienteActualizado = crearObjetoPaciente(resultSet);
            }
            LOGGER.info("Se logró modificar el odontologo " + pacienteActualizado);
            connection.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return pacienteActualizado;
    }

    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        Domicilio domicilio = domicilioDaoH2.buscarPorId(resultSet.getInt("ID_DOMICILIO"));
        return new Paciente(resultSet.getInt("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"), resultSet.getInt("DNI"), resultSet.getDate("FECHA_INGRESO").toLocalDate(), domicilio);
    }
}
