package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;
}
