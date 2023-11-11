package com.backend.clinica_odontologica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class ClinicaOdontologicaApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		crearTabla();
		LOGGER.info("ClinicaOdontológica is running ...");
	}
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

}
