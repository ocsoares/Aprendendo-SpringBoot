package com.ocsoares.aprendendospringboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(@NotBlank String name, @NotNull Double price) {
}
