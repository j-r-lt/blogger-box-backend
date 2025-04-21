package com.dauphine.blogger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.UUID;

@Entity
@Table(name= "category")

public class Category {

    @Id
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
