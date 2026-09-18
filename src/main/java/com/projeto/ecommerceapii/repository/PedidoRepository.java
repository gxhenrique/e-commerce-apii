package com.projeto.ecommerceapii.repository;

import com.projeto.ecommerceapii.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findAll(Pageable pageable);
    boolean existsByClienteId(Long clienteId);

    Optional<Pedido> findByIdAndClienteId(Long pedidoId, Long clienteId);
    Page<Pedido> findAllByClienteId(Long clienteId, Pageable pageable);
}
