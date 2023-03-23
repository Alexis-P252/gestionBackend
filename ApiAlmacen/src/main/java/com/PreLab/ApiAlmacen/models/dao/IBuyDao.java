package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Buy;
import org.springframework.data.repository.CrudRepository;

public interface IBuyDao extends CrudRepository<Buy,Long> { }
