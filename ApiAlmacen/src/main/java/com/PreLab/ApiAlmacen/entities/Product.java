package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
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
    private Integer buy_price;
    @Column
    private Integer sell_price;
    @Column
    private Integer stock;
    @Column
    private String image;
    @Column
    private Boolean visible;

    @ManyToOne
    private Category category;
}
