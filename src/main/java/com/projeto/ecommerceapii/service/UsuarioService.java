package com.projeto.ecommerceapii.service;

import com.projeto.ecommerceapii.config.TokenConfig;
import com.projeto.ecommerceapii.dto.usuario.login.LoginResponse;
import com.projeto.ecommerceapii.dto.usuario.login.RequestLogin;
import com.projeto.ecommerceapii.entity.Usuario;
import com.projeto.ecommerceapii.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authenticationManager;

    public UsuarioService(UsuarioRepository usuarioRepository, TokenConfig tokenConfig, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
    }



    public LoginResponse login (RequestLogin dto){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        Usuario user = (Usuario) authentication.getPrincipal();

        String token = tokenConfig.generateToken(user);

        return new LoginResponse(token);
    }




}