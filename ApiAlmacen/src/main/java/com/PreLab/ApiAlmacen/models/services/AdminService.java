package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Admin;
import com.PreLab.ApiAlmacen.models.dao.IAdminDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminDao adminDao;

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) adminDao.findAll();
    }

    @Override
    public Admin findById(Long id) {
        return adminDao.findById(id).orElse(null);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username){
        return adminDao.existsByUsername(username);
    }

    @Override
    public Admin save(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public void deleteById(Long id) {
        adminDao.deleteById(id);
    }

    @Override
    public boolean verifyLogin(String username, String password) {
        if(adminDao.existsByUsername(username)){
            Admin a = adminDao.findByUsername(username);
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            return argon2.verify(a.getPassword(),password);
        }
        else{
            return false;
        }
    }
}
