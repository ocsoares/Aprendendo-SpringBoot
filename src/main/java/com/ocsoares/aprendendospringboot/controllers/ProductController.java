package com.ocsoares.aprendendospringboot.controllers;

import com.ocsoares.aprendendospringboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    // PROCURAR como usar esse Autowired!!!
    @Autowired
    ProductRepository productRepository;
}
