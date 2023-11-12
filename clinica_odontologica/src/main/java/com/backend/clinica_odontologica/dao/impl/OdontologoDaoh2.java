package com.backend.clinica_odontologica.dao.impl;

import com.backend.clinica_odontologica.dao.H2Connection;
import com.backend.clinica_odontologica.dao.IDao;
import com.backend.clinica_odontologica.model.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoh2 implements IDao<Odontologo> {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoh2.class);

    public OdontologoDaoh2() {
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Odontologo odontologoPersistido = null;
        Connection connection = null;
        String insert = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) \n" +
                "VALUES (?, ?, ?)";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoPersistido = new Odontologo(resultSet.getInt(1), odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            LOGGER.info("Se logró registrar el odontologo: " + odontologoPersistido);
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
        return odontologoPersistido;
    }

    @Override
    public List<Odontologo> listar() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        String select = "SELECT * FROM ODONTOLOGOS";
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
            }

            LOGGER.info("Listado de odontologos obtenido: " + odontologos);

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
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Odontologo odontologo = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            LOGGER.info("Método buscarPorId clase OdontologoDaoH2");
            if (odontologo == null) LOGGER.error("No se ha encontrado el odontologo con id: " + id);
            else LOGGER.info("Se ha encontrado el odontologo: " + odontologo);

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
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoModificado = null;
        String update = "UPDATE ODONTOLOGOS SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?";
        String select = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.setInt(4, odontologo.getId());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(select);
            preparedStatement.setInt(1, odontologo.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologoModificado = new Odontologo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
            LOGGER.info("Se logró modificar el odontologo " + odontologoModificado);
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


        return odontologoModificado;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        return new Odontologo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
    }
}
