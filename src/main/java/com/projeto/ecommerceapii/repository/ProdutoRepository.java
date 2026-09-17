package com.projeto.ecommerceapii.repository;

import com.projeto.ecommerceapii.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    Page<Produto> findAll(Pageable pageable);
    boolean existsByCategoriaId(Long categoriaId);

    Optional<Produto> findByNome(String nome);
}
