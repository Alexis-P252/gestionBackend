package com.PreLab.ApiAlmacen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private String name;

    @Column
    private String phoneNumber;

    @Column
    @PositiveOrZero
    private Float debt;

    @PrePersist
    private void prePersist(){
        // check that debt cannot be < 0.
        if(this.debt < 0){
            this.debt = 0F;
        }
    }




}
