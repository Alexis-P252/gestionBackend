package com.PreLab.ApiAlmacen.models.services;

import com.PreLab.ApiAlmacen.entities.Client;

import java.util.List;

public interface IClientService {

    public List<Client> findAll();

    public List<Client> findWithDebt();

    public Client findById(Long id);

    public Client save(Client client);

    public void deleteById(Long id);
}
