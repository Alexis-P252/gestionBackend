package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductDao extends CrudRepository<Product,Long> {
}
