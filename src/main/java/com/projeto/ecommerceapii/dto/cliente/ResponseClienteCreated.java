package com.projeto.ecommerceapii.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ResponseClienteCreated (

        String nome,
        String email,
        String cpf){
}
