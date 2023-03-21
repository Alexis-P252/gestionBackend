package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Offer;
import com.PreLab.ApiAlmacen.models.dao.IOfferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OfferService implements IOfferService {

    @Autowired
    private IOfferDao offerDao;
    @Override
    public List<Offer> findAll(){return  (List<Offer>) offerDao.findAll();}

    @Override
    public Offer findById(Long id) {
        return offerDao.findById(id).orElse(null);
    }

    @Override
    public Offer save(Offer user) {return offerDao.save(user);}

    @Override
    public void deleteById(Long id) {offerDao.deleteById(id);}

}
