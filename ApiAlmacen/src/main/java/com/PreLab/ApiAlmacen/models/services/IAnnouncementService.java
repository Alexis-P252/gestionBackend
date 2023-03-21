package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Announcement;

import java.util.List;

public interface IAnnouncementService {
    public List<Announcement> findAll();

    public Announcement findById(Long id);

    public Announcement save(Announcement announcement);

    public void deleteById(Long id);
}
