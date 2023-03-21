package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Offer;
import org.springframework.data.repository.CrudRepository;

public interface IOfferDao extends CrudRepository<Offer, Long> {}
