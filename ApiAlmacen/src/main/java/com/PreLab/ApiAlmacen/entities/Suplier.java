package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "suplier")
public class Suplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private String name;
    @Column
    @NotEmpty
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String image;
}
