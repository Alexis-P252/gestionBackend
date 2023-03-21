package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Offer;

import java.util.List;

public interface IOfferService {
    public List<Offer> findAll();

    public Offer findById(Long id);

    public Offer save(Offer offer);

    public void deleteById(Long id);
}
