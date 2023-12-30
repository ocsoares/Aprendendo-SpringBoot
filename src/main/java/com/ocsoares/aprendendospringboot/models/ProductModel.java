package com.ocsoares.aprendendospringboot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
@Data
public class ProductModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // Número de Controle de VERSÃO da Classe!!
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // GERA e SALVA os "ids" AUTOMATICAMENTE!!!
    private UUID id;
    private String name;
    private Double price;
}
