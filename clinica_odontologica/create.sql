DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, CALLE VARCHAR(250) NOT NULL, NUMERO INT NOT NULL, LOCALIDAD VARCHAR(250) NOT NULL, PROVINCIA VARCHAR(100) NOT NULL);

DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, DNI INT NOT NULL, FECHA_INGRESO DATE NOT NULL, ID_DOMICILIO INT NOT NULL);
DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL, MATRICULA VARCHAR(100) NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL);

INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA)
VALUES ('Bolivar', 10, 'Chacao', 'Caracas'),
('San Martin', 20, 'Pilar', 'Buenos Aires');

INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, ID_DOMICILIO)
VALUES ('Wil', 'Pereira', 95824357, '2023-05-10', 1),
('Lili', 'Candia', 95905206, '2023-01-01', 2);

INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO)
VALUES ('95824357A', 'Coromoto', 'Candia'),
('95905206B', 'Alejandro', 'Carreno');