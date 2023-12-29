package com.ocsoares.aprendendospringboot.controllers;

import com.ocsoares.aprendendospringboot.dtos.ProductRecordDTO;
import com.ocsoares.aprendendospringboot.models.ProductModel;
import com.ocsoares.aprendendospringboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    // NÃO é necessário usar o "@Autowired" aqui porque tem APENAS UM ÚNICO Constructor, se houvessem mais seria
    // NECESSÁRIO explicitar o "@Autowired" no Código!!!
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // A annotation "@Valid" serve para ATIVAR a VALIDAÇÃO do DTO, como "@NotBlank" e "@NotNull", por exemplo !!!
    @PostMapping("product")
    public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel); // TRANSFORMA o "productRecordDTO" em "productModel" !
        ProductModel savedProduct = productRepository.save(productModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("product")
    public ResponseEntity<List<ProductModel>> getAll() {
        List<ProductModel> productModelList = productRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(productModelList);
    }

    // Usando "ResponseEntity<Object>" porque o "product" pode NÃO Existir!!!
    @GetMapping("product/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productFound = productRepository.findById(id);

        if (productFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productFound.get());
    }
}
