package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Category;
import com.PreLab.ApiAlmacen.models.dao.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);

    }
}
