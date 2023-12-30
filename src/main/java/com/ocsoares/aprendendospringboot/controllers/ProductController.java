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
//@RequiredArgsConstructor // TAMBÉM pode usar isso e RETIRAR o Constructor, mas vai fazer para TODOS os Atributos "final" !!
public class ProductController {
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found";
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PRODUCT_NOT_FOUND_MESSAGE);
        }
        
        // Esse é a MESMA COISA que usar com o "status(HttpStatus.OK).body(productFound.get()) !!!
        return ResponseEntity.ok(productFound.get());
    }

    @PatchMapping("product/{id}")
    public ResponseEntity<Object> updateOne(
            @PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDTO productRecordDTO
    ) {
        Optional<ProductModel> productFound = productRepository.findById(id);

        if (productFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PRODUCT_NOT_FOUND_MESSAGE);
        }

        ProductModel productModel = productFound.get();
        BeanUtils.copyProperties(productRecordDTO, productModel); // ATUALIZA o "product" com as NOVAS Informações!!!

        ProductModel updatedProduct = productRepository.save(productModel); // SALVA esse Novo "product" ATUALIZADO!!!

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productFound = productRepository.findById(id);

        if (productFound.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PRODUCT_NOT_FOUND_MESSAGE);
        }

        productRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
