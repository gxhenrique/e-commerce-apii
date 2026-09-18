package com.projeto.ecommerceapii.config;

public record JWTUserData(
        Long userId,
        Long clienteId,
        String email,
        String role) {
}
