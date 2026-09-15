package com.projeto.ecommerceapii.dto.pedido;

import com.projeto.ecommerceapii.dto.ResponseItemPedidoDTO;
import com.projeto.ecommerceapii.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ResponsePedidoDTO(Long id, LocalDateTime data, Status status, BigDecimal total, List<ResponseItemPedidoDTO> itens) {
}
