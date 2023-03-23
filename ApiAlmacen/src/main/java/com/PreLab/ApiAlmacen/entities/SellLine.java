package com.PreLab.ApiAlmacen.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sellLine")
public class SellLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity;

    @Column
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;



}
