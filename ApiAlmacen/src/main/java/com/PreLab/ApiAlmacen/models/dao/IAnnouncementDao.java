package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface IAnnouncementDao extends CrudRepository<Announcement, Long> {}
