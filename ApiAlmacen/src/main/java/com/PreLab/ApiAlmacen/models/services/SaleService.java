package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Sale;
import com.PreLab.ApiAlmacen.models.dao.ISaleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleService implements ISaleService{

    @Autowired
    private ISaleDao saleDao;

    @Override
    public List<Sale> findAll() {
        return (List<Sale>) saleDao.findAll();
    }

    @Override
    public Sale findById(Long id) {
        return saleDao.findById(id).orElse(null);
    }

    @Override
    public Sale save(Sale sale) {
        return saleDao.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        saleDao.deleteById(id);

    }
}
