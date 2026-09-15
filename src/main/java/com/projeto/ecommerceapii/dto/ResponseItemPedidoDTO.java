package com.projeto.ecommerceapii.dto;

import java.math.BigDecimal;

public record ResponseItemPedidoDTO(
        Long id,
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario
) {}