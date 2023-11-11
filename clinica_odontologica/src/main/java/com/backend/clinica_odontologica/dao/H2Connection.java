package com.backend.clinica_odontologica.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    private static Logger LOGGER = LoggerFactory.getLogger(H2Connection.class);

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/db_clinica_odontologica", "sa", "sa");
    }
}
