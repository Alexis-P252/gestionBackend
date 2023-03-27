package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.LowHigh;
import org.springframework.data.repository.CrudRepository;

public interface ILowHighDao extends CrudRepository<LowHigh, Long> {
}
