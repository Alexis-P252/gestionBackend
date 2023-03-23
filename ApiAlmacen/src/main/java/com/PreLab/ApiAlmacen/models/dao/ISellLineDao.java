package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.SellLine;
import org.springframework.data.repository.CrudRepository;

public interface ISellLineDao extends CrudRepository<SellLine, Long> {
}
