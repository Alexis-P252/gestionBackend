package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.entities.Category;

import java.util.List;

public interface IBuyService {
    public List<Buy> findAll();

    public Buy findById(Long id);

    public Buy save(Buy buy);

    public void deleteById(Long id);
}
