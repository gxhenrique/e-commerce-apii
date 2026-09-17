package com.projeto.ecommerceapii.dto.produto;

import com.projeto.ecommerceapii.entity.Categoria;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ResponseProdutoDTO(
        Long id,
        String nome,
        String decricao,
        BigDecimal preco,
        Integer estoque,
        Boolean ativo,
        String categoria) {

}
