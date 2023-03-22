package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Category;

import java.util.List;

public interface ICategoryService {

    public List<Category> findAll();

    public Category findById(Long id);

    public Category save(Category category);

    public void deleteById(Long id);

}
