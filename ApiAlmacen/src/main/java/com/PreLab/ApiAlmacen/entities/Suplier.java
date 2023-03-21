package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "suplier")
public class Suplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private Number phoneNumber;
    @Column
    private String address;
    @Column
    private String Long;
}
