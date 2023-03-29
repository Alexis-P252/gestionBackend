package com.PreLab.ApiAlmacen.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "sellLine")
public class SellLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Positive
    private Integer quantity;

    @Column
    @Positive
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;



}
