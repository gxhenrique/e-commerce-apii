package com.projeto.ecommerceapii.dto.itemPedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateItemPedidoDTO(
        @NotNull(message = "Digite um id valido")
        Long produtoId,
        @NotNull
        @Min(value = 1, message = "Minimo 1")
        Integer quantidade) {
}
