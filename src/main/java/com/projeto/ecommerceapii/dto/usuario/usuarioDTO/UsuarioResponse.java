package com.projeto.ecommerceapii.dto.usuario.usuarioDTO;

import com.projeto.ecommerceapii.entity.Role;

public record UsuarioResponse(String email, Role role, Long clienteId) {
}
