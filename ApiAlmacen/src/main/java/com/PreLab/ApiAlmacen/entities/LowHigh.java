package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "lowHigh")
public class LowHigh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotEmpty
    private Boolean lowHigh; // true = high, false = low
    @Column
    private Date date;
    @Column
    private String reason;
    @Column
    @Positive
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;


}
