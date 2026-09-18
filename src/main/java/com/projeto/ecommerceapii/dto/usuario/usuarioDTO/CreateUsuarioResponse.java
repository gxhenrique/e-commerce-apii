package com.projeto.ecommerceapii.dto.usuario.usuarioDTO;

import com.projeto.ecommerceapii.entity.Role;

public record CreateUsuarioResponse(String email, String senha, Role role, Long clienteId) {
}
