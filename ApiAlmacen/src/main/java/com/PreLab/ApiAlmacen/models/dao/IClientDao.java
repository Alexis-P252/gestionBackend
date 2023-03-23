package com.PreLab.ApiAlmacen.models.dao;

import com.PreLab.ApiAlmacen.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClientDao extends CrudRepository<Client, Long> {

    List<Client> findByDebtGreaterThan(double number);
}
