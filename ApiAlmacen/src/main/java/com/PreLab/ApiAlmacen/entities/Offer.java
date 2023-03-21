package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Boolean visible;
}
