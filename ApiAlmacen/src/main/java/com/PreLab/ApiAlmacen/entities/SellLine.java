package com.PreLab.ApiAlmacen.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "sellLine")
public class SellLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private Sale sale;


}
