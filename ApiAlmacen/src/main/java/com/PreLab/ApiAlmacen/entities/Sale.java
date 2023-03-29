package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String comment;

    @Column
    private String paymentMethod;

    @Column
    @Positive
    private Integer total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private List<SellLine> sellLines;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

}
