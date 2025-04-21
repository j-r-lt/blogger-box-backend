package com.dauphine.blogger.models;

import jakarta.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Entity
@Table(name= "category")

public class Category {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name=name;
    }


    public UUID getId() {
        return id;
    }

    public void setUuid(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
