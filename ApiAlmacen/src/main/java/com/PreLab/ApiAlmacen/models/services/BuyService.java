package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.entities.Category;
import com.PreLab.ApiAlmacen.models.dao.IBuyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyService implements IBuyService{

    @Autowired
    private IBuyDao buyDao;

    @Override
    public List<Buy> findAll(){ return (List<Buy>) buyDao.findAll();};
    @Override
    public Buy findById(Long id){return buyDao.findById(id).orElse(null);};
    @Override
    public Buy save(Buy buy){ return buyDao.save(buy);};
    @Override
    public void deleteById(Long id){buyDao.deleteById(id);};

}
