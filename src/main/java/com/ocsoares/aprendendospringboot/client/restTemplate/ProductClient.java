package com.ocsoares.aprendendospringboot.client.restTemplate;

import com.ocsoares.aprendendospringboot.models.ProductModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class ProductClient {
    private static final String URL = "http://localhost:8080";

    public static void main(String[] args) {
        // Pode passar DIRETAMENTE o "id" no lugar de {id}, mas usando assim fica mais Organizado se houver VÁRIOS Paths
        // de URL!!!
        ProductModel getProductByIdClient = new RestTemplate().getForObject(
                URL + "/product/{id}", ProductModel.class, "c7a1a95c-1d40-49cb-a377-75f1c80ecbbd");

        assert getProductByIdClient != null;

        log.info("getProductByIdClient: " + getProductByIdClient);
        log.info("ID: " + getProductByIdClient.getId());
        log.info("name: " + getProductByIdClient.getName());
        log.info("price: " + getProductByIdClient.getPrice());

        System.out.println("\n");

        // O "null" se refere ao "requestEntity" que são os HEADERS da Requisição!!!
        // ----------------------------------------------------------------------------------------------------
        // O "new ParameterizedTypeReference<>" TRANSFORMA no Tipo de RETORNO Declarado, que nesse caso
        // é "ResponseEntity<List<ProductModel>>" !!!
        ResponseEntity<List<ProductModel>> getAllProductsList = new RestTemplate().exchange(
                URL + "/product", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        log.info("getAllProductsList: " + getAllProductsList.getBody());
    }
}
