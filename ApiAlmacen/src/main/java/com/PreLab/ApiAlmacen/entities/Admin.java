package com.PreLab.ApiAlmacen.entities;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column String password;

    @PrePersist
    public void prePersist() {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, password);
        this.password = hash;
    }
}
