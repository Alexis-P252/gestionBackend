package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.LowHigh;

import java.util.List;

public interface ILowHighService {

    public List<LowHigh> findAll();

    public LowHigh findById(Long id);

    public LowHigh save(LowHigh lowHigh);

    public void deleteById(Long id);
}
