package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
    @NotEmpty
    private String name;
    @Column
    private String description;
    @Column
    @Positive
    private Integer buy_price;
    @Column
    @Positive
    private Integer sell_price;
    @Column
    @PositiveOrZero
    private Integer stock;
    @Column
    private String image;
    @Column
    private Boolean visible;

    @ManyToOne
    private Category category;
}
