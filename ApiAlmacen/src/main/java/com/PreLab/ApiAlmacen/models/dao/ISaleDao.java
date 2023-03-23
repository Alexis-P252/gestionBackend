package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Sale;
import org.springframework.data.repository.CrudRepository;

public interface ISaleDao extends CrudRepository<Sale, Long> {
}
