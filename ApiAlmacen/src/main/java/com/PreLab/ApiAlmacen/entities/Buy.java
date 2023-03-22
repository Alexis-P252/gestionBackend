package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
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
    private Date date;

    @Column
    private Long comment;

    @Column
    private Integer total;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private List<BuyLine> buyLine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suplier_id")
    private Suplier suplier;



}
