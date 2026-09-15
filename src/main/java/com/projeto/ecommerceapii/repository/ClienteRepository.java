package com.projeto.ecommerceapii.repository;

import com.projeto.ecommerceapii.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Optional<Cliente> findByCpf(String cpf);
}
