package com.projeto.ecommerceapii.dto.pedido;

import com.projeto.ecommerceapii.dto.itemPedido.CreateItemPedidoDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreatePedidoDTO(

        @NotNull
        Long clienteId,
        @NotNull
        List<CreateItemPedidoDTO> itens) {
}
