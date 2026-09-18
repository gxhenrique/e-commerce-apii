package com.projeto.ecommerceapii.controller;

import com.projeto.ecommerceapii.dto.usuario.login.LoginResponse;
import com.projeto.ecommerceapii.dto.usuario.login.RequestLogin;
import com.projeto.ecommerceapii.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody RequestLogin dto){
        return ResponseEntity.ok(usuarioService.login(dto));
    }
}
