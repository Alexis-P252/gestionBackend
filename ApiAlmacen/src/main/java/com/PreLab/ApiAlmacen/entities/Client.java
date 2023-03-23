package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private Float debt;

    @PrePersist
    private void prePersist(){
        // check that debt cannot be < 0.
        if(this.debt < 0){
            this.debt = 0F;
        }
    }




}
