package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.SellLine;

public interface ISellLineService {

    public SellLine findById(Long id);
    public void deleteById(Long id);
}
