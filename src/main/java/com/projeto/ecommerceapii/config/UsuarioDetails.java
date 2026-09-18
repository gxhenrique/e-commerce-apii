package com.projeto.ecommerceapii.config;

import com.projeto.ecommerceapii.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetails implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetails(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
