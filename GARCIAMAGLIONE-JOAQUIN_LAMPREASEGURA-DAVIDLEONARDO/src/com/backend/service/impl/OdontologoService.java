package com.backend.service.impl;

import com.backend.dao.IDao;
import com.backend.entity.Odontologo;
import com.backend.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> OdontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.OdontologoIDao = odontologoIDao;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return OdontologoIDao.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listarTodos();
    }

    @Override
    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }
}
