package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Suplier;
import com.PreLab.ApiAlmacen.models.dao.ISuplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuplierService implements ISuplierService{

    @Autowired
    private ISuplierDao suplierDao;

    @Override
    public List<Suplier> findAll() {
        return (List<Suplier>) suplierDao.findAll();
    }

    @Override
    public Suplier findById(Long id) {
        return suplierDao.findById(id).orElse(null);
    }

    @Override
    public Suplier save(Suplier suplier) {
        return suplierDao.save(suplier);
    }

    @Override
    public void deleteById(Long id) {
        suplierDao.deleteById(id);

    }
}
