package com.projeto.ecommerceapii.dto.cliente;

import com.projeto.ecommerceapii.dto.pedido.ResponsePedidoDTO;

import java.util.List;

public record ResponseCLienteDTO(Long id, String nome, String email, String cpf, List<ResponsePedidoDTO> pedidos) {
}
