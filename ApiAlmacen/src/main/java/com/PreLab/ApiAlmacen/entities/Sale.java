package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String comment;

    @Column
    private String paymentMethod;
}
