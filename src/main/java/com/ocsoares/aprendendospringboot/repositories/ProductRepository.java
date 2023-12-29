package com.ocsoares.aprendendospringboot.repositories;

import com.ocsoares.aprendendospringboot.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// Dentro dos Generics "< >" passar o Model e o Identificador do ID usado nesse Model!!!
@Repository // Esse "@Repository" NÃO é necessário, porque o "JpaRepository" por PADRÃO já usa ele Implicitamente!!!
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
