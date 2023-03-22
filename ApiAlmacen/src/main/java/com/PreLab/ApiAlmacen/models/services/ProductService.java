package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Product;
import com.PreLab.ApiAlmacen.models.dao.IProductDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional
    public List<Product> findAll() {
        return (List<Product>) productDao.findAll();
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }
}
