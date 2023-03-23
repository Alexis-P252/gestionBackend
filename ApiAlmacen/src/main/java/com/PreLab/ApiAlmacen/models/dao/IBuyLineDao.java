package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Buy;
import com.PreLab.ApiAlmacen.entities.BuyLine;
import org.springframework.data.repository.CrudRepository;

public interface IBuyLineDao extends CrudRepository<BuyLine,Long> { }
