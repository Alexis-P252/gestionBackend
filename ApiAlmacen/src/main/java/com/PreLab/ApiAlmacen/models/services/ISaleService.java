package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Sale;
import com.PreLab.ApiAlmacen.exceptions.NotEnoughStockException;

import java.util.List;

public interface ISaleService {
    public List<Sale> findAll();

    public Sale findById(Long id);

    public Sale save(Sale sale) throws NotEnoughStockException;

    public void deleteById(Long id);
}
