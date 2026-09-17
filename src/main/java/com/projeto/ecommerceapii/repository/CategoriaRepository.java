package com.projeto.ecommerceapii.repository;

import com.projeto.ecommerceapii.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findAll(Pageable pageable);
    Optional<String> findByNome(String nome);

}
