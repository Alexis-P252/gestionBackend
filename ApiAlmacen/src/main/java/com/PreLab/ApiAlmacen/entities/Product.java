package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float price;
    @Column
    private Integer stock;
    @Column
    private Long image;
    @Column
    private Boolean visible;
    @OneToMany
    private List<SellLine> sellLine;
    @OneToMany
    private List<LowHigh> lowHigh;

}
