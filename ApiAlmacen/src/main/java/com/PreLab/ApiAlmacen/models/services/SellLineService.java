package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.SellLine;
import com.PreLab.ApiAlmacen.models.dao.ISellLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellLineService implements ISellLineService {

    @Autowired
    private ISellLineDao sellLineDao;

    @Override
    public SellLine findById(Long id) {
        return sellLineDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        sellLineDao.deleteById(id);

    }
}
