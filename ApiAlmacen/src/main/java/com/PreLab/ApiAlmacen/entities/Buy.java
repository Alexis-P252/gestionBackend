package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "buy")
public class Buy {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private Date date;

    @Column
    private String comment;

    @Column
    @Positive
    private Integer total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "buy_id")
    private List<BuyLine> buyLine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suplier_id")
    @NotEmpty
    private Suplier suplier;



}
