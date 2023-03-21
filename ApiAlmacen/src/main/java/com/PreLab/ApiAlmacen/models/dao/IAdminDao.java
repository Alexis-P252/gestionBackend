package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface IAdminDao extends CrudRepository<Admin, Long> {

    Admin findByUsername(String username);

    boolean existsByUsername(String username);
}
