package com.projeto.ecommerceapii.dto.produto;

import java.math.BigDecimal;

public record UpdateProdutoDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque,
        Boolean ativo,
        Long categoriaId) {
}
