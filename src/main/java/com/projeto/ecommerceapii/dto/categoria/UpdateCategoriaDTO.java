package com.projeto.ecommerceapii.dto.categoria;

import jakarta.validation.constraints.NotNull;

public record UpdateCategoriaDTO(@NotNull String nome) {
}
