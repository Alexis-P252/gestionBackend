package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Admin;

import java.util.List;

public interface IAdminService {

    public List<Admin> findAll();

    public Admin findById(Long id);

    public Admin findByUsername(String username);

    public boolean existsByUsername(String username);

    public Admin save(Admin admin);

    public void deleteById(Long id);

    public boolean verifyLogin(String username, String password);

}
