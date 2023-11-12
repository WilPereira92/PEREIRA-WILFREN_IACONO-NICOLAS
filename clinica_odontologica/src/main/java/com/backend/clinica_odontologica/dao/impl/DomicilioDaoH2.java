package com.backend.clinica_odontologica.dao.impl;

import com.backend.clinica_odontologica.dao.H2Connection;
import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.model.Domicilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDaoH2 implements IDao<Domicilio> {
    private static Logger LOGGER = LoggerFactory.getLogger(DomicilioDaoH2.class);

    @Override
    public Domicilio registrar(Domicilio domicilio) {
        Connection connection = null;
        Domicilio domicilioRegistrado = null;
        String insert = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?, ?, ?, ?)";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            domicilioRegistrado = new Domicilio(domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(), domicilio.getProvincia());

            while (resultSet.next()) {
                domicilioRegistrado.setId(resultSet.getInt("id"));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el domicilio: " + domicilioRegistrado);
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

        return domicilioRegistrado;
    }

    @Override
    public List<Domicilio> listar() {
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();
        String select = "SELECT * FROM DOMICILIOS";
        try {
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Domicilio domicilio = crearObjetoDomicilio(resultSet);
                domicilioList.add(domicilio);

            }

            LOGGER.info("Listado de domicilios obtenido: " + domicilioList);

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


        return domicilioList;
    }

    @Override
    public Domicilio buscarPorId(int id) {
        Domicilio domicilio = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID = ?");
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
            LOGGER.info("Método buscarPorId clase DomicilioDaoH2");
            if (domicilio == null) LOGGER.error("No se ha encontrado el domicilio con id: " + id);
            else LOGGER.info("Se ha encontrado el domicilio: " + domicilio);

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
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;
        Domicilio domicilioActualizado = null;
        String select = "SELECT * FROM DOMICILIOS WHERE ID = ?";
        String update = "UPDATE DOMICILIOS SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE ID = ?";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, domicilio.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                domicilioActualizado = crearObjetoDomicilio(resultSet);
            }
            if (domicilioActualizado == null) {
                LOGGER.info("El domicilio con id " + domicilioActualizado.getId() + " no existe en la BDD");
            } else {
                preparedStatement = connection.prepareStatement(update);
                preparedStatement.setString(1, domicilio.getCalle());
                preparedStatement.setInt(2, domicilio.getNumero());
                preparedStatement.setString(3, domicilio.getLocalidad());
                preparedStatement.setString(4, domicilio.getProvincia());
                preparedStatement.setInt(5, domicilio.getId());
                preparedStatement.execute();
                LOGGER.info("Se modificó el domicilio con el id " + domicilio.getId() + " " + domicilioActualizado);
            }

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


        return domicilioActualizado;
    }

    private Domicilio crearObjetoDomicilio(ResultSet resultSet) throws SQLException {

        return new Domicilio(resultSet.getInt("id"), resultSet.getString("calle"), resultSet.getInt("numero"), resultSet.getString("localidad"), resultSet.getString("provincia"));

    }
}
