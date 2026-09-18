package com.projeto.ecommerceapii.config;

import com.projeto.ecommerceapii.entity.Cliente;
import com.projeto.ecommerceapii.entity.Role;
import com.projeto.ecommerceapii.entity.Usuario;
import com.projeto.ecommerceapii.exception.EntityNotFoundException;
import com.projeto.ecommerceapii.repository.ClienteRepository;
import com.projeto.ecommerceapii.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dataloader {

    private final ClienteRepository clienteRepository;

    public Dataloader(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    //joao@email.com', "123456","CLIENTE", 1
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder){


        return args -> {

            Cliente cliente = clienteRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));
            Cliente cliente2 = clienteRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));
            Cliente cliente3 = clienteRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("Cliente não encotrado"));
            List<Usuario> list = new ArrayList<>();

            list.add(new Usuario(cliente,Role.CLIENTE,passwordEncoder.encode("123456"),"joao@email.com"));
            list.add(new Usuario(cliente2,Role.CLIENTE,passwordEncoder.encode("123456"),"maria@email.com"));
            list.add(new Usuario(cliente3,Role.ADMIN,passwordEncoder.encode("123456"),"carlos@email.com"));

            for (Usuario user : list){
                usuarioRepository.save(user);
            }
        };
    }
}
