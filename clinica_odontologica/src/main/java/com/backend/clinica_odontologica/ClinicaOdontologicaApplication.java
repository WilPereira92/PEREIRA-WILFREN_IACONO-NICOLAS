package com.backend.clinica_odontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClinicaOdontologicaApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClinicaOdontologicaApplication.class, args);
        LOGGER.info("ClinicaOdontológica is running ...");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /*
    DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, CALLE VARCHAR(250) NOT NULL, NUMERO INT NOT NULL, LOCALIDAD VARCHAR(250) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, DNI INT NOT NULL, FECHA_INGRESO DATE NOT NULL, ID_DOMICILIO INT NOT NULL);
DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, MATRICULA VARCHAR(100) NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL);
 private static void crearTabla() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica_odontologica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
     */
}
