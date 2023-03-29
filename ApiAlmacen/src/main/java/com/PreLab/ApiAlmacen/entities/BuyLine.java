package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Cleanup;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import java.io.Serializable;

@Entity
@Data
@Table(name = "buyLine")
public class BuyLine implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    @Positive
    private Integer quantity;

    @Column
    @Positive
    private Integer price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    private static final long serialVersionUID = 1L;





}
