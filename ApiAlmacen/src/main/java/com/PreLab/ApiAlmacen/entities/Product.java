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
    private String image;
    @Column
    private Boolean visible;
    @OneToMany
    @JoinColumn(name = "fk_id_selline)")
    private List<SellLine> sellLine;
    @OneToMany
    @JoinColumn(name = "fk_id")
    private List<LowHigh> lowHigh;

}
