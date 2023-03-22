package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Admin;
import com.PreLab.ApiAlmacen.entities.Suplier;

import java.util.List;

public interface ISuplierService {

    public List<Suplier> findAll();

    public Suplier findById(Long id);

    public Suplier save(Suplier suplier);

    public void deleteById(Long id);

}
