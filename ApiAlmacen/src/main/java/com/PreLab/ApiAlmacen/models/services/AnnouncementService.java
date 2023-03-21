package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Announcement;
import com.PreLab.ApiAlmacen.models.dao.IAnnouncementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    private IAnnouncementDao announcementDao;
    @Override
    public List<Announcement> findAll(){return  (List<Announcement>) announcementDao.findAll();}

    @Override
    public Announcement findById(Long id) {
        return announcementDao.findById(id).orElse(null);
    }

    @Override
    public Announcement save(Announcement user) {return announcementDao.save(user);}

    @Override
    public void deleteById(Long id) {
        announcementDao.deleteById(id);
    }




}
