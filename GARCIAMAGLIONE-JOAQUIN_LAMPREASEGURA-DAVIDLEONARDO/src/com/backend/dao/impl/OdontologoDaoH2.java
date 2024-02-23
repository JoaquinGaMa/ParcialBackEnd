package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo OdontologoRegistrado = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setInt(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            odontologoRegistrado = new Odontologo(odontologo.getNumeroMatricula(), odontologo.getNombre(), resultSet.getInt("numero Matricula"), odontologo.getApellido());

            while (resultSet.next()){
                odontologoRegistrado.setId(resultSet.getInt("id"));
            }


            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologoRegistrado);

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

        return odontologoRegistrado;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Odontologo domicilio = null;
        Connection connection = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGO WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                odontologo = crearObjetoOdontologo(resultSet);
            }

            if(domicilio == null) LOGGER.error("No se ha encontrado el odontologo con id: " + id);
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
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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


    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {

        return new Odontologo(resultSet.getInt("id"), resultSet.getString("Nombre"), resultSet.getInt("Numero Matricula"),resultSet.getString("Apellido"));
    }
}
