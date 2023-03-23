package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.entities.Category;

import java.util.List;

public class BuyService implements IBuyService{

    IBuyService iBuyService;
    @Override
    public List<Buy> findAll(){ return iBuyService.findAll();};
    @Override
    public Buy findById(Long id){return iBuyService.findById(id);};
    @Override
    public Buy save(Buy buy){ return iBuyService.save(buy);};
    @Override
    public void deleteById(Long id){iBuyService.deleteById(id);};

}
