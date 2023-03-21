package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "lowHigh")
public class LowHigh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean lowHigh;
    @Column
    private Date date;
    @Column
    private String reason;
}
