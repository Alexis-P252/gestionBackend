package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Cleanup;
import org.hibernate.engine.internal.Cascade;

import java.io.Serializable;

@Entity
@Table(name = "buyLine")
public class BuyLine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_buyer")
    private Buy buy;


}
