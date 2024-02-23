package com.backend.test;

import com.backend.dao.impl.PacienteDaoH2;
import com.backend.entity.Odontologo;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaRegistrarUnOdontologoYRetornarUnIdEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo(123456, "Nombre", resultSet.getInt("numero Matricula"), "Apellido");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        Assertions.assertTrue(odontologoRegistrado.getId() != 0);

    }

    @Test
    void deberiaRegistrarUnOdontologoYRetornarUnIdEnMemoria(){
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        Odontologo odontologo = new Odontologo(123456, "Nombre", resultSet.getInt("numero Matricula"), "Apellido");

        Odontologo odontologoRegistrado = odontologoService.registrarPaciente(odontologo);

        Assertions.assertTrue(odontologoRegistrado.getId() != 0);

    }


}