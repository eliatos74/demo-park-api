package com.eliassantos.demoparkapi.web.dto.mappers;

import com.eliassantos.demoparkapi.entity.Usuario;
import com.eliassantos.demoparkapi.web.dto.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioResponseDTO getResponseByIntity(Usuario user) {
        return new UsuarioResponseDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
