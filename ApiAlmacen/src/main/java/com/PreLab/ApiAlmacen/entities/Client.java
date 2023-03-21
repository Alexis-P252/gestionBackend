package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
