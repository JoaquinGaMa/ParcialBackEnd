DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGO (NUMEROMATRICULA INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(50), APELLIDO VARCHAR(50));
CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL, DNI INT NOT NULL, FECHA DATE NOT NULL, NUMEROMATRICULA_ID INT);



-- para test --
INSERT INTO ODONTOLOGOS(NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES ( 123742, 'CARLITOS', 'REUTTEMAN');


