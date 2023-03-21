package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private Float debt;

    @OneToMany(mappedBy = "client")
    private List<Sale> sales;

}
