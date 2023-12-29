package com.ocsoares.aprendendospringboot.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Número de Controle de VERSÃO da Classe!!
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // GERA e SALVA os "ids" AUTOMATICAMENTE!!!
    private UUID id;
    private String name;
    private Double price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
