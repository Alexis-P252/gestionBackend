package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "suplier")
public class Suplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String image;
}
