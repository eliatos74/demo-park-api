package com.eliassantos.demoparkapi.web.dto;

import com.eliassantos.demoparkapi.entity.Usuario;

public record UsuarioResponseDTO(Long id, String username, Usuario.Role role) {
    public static class UsuarioSenhaDTO {
    }
}
