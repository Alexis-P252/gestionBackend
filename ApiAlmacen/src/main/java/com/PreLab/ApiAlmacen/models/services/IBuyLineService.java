package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.BuyLine;

import java.util.List;

public interface IBuyLineService {
    public BuyLine findById(Long id);
    public void deleteById(Long id);
}
