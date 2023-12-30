package com.ocsoares.aprendendospringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException() {
        super("Product not found");
    }
}
