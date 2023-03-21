package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

import java.util.Base64;

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
    private Base64 Long;
}
