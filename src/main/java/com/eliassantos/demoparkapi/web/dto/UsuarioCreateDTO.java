package com.eliassantos.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateDTO(
        @NotBlank
        @Email(regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Formato de e-mail invalido.")
        String username,

        @NotBlank
        @Size(min = 6, max = 6)
        String password) {
}