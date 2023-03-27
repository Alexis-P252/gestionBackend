package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.LowHigh;
import com.PreLab.ApiAlmacen.entities.Product;
import com.PreLab.ApiAlmacen.models.dao.ILowHighDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LowHighService implements ILowHighService {

    @Autowired
    private ILowHighDao lowHighDao;

    @Autowired
    private IProductService productService;

    @Override
    public List<LowHigh> findAll() {
        return (List<LowHigh>) lowHighDao.findAll();
    }

    @Override
    public LowHigh findById(Long id) {
        return lowHighDao.findById(id).orElse(null);
    }

    @Override
    public LowHigh save(LowHigh lowHigh) {

        Product product = lowHigh.getProduct();
        if(lowHigh.getLowHigh()){
            product.setStock(product.getStock() + lowHigh.getQuantity());
        }
        else{
            product.setStock(product.getStock() - lowHigh.getQuantity());
        }
        productService.save(product);

        return lowHighDao.save(lowHigh);
    }

    @Override
    public void deleteById(Long id) {
         lowHighDao.deleteById(id);

    }
}
