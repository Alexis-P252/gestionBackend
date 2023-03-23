package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Sale;

import java.util.List;

public interface ISaleService {
    public List<Sale> findAll();

    public Sale findById(Long id);

    public Sale save(Sale sale);

    public void deleteById(Long id);
}
