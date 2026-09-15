package com.projeto.ecommerceapii.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateClienteDTO(
        @NotNull
        String nome,
        @NotNull
        @Email(message = "Email invalido")
        String email,
        @NotNull
        String cpf) {
}
