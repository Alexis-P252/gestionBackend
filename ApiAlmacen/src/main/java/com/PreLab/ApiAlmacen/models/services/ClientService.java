package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Client;
import com.PreLab.ApiAlmacen.models.dao.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService{
    @Autowired
    private IClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    public List<Client> findWithDebt(){
        return (List<Client>) clientDao.findByDebtGreaterThan(0);
    }

    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }
}
