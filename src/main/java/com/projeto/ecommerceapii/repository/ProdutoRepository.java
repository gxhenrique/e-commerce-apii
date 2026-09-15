package com.projeto.ecommerceapii.repository;

import com.projeto.ecommerceapii.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    boolean existsByCategoriaId(Long categoriaId);
}
