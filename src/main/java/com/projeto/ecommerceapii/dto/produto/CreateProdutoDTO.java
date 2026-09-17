package com.projeto.ecommerceapii.dto.produto;

import com.projeto.ecommerceapii.entity.Categoria;

import java.math.BigDecimal;

public record CreateProdutoDTO(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque,
        Boolean ativo,
        Long categoriaId) {
}
