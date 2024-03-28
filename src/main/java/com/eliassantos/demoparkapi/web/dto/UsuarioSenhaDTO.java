package com.eliassantos.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioSenhaDTO(
        @NotBlank
        @Size(min = 6, max = 6)
        String senhaAtual,

        @NotBlank
        @Size(min = 6, max = 6)
        String novaSenha,

        @NotBlank
        @Size(min = 6, max = 6)
        String confirmarSenha) {
}
