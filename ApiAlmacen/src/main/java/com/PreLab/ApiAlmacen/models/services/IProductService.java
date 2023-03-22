package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public Product save(Product product);

    public void deleteById(Long id);
}
