package com.eliassantos.demoparkapi.web.dto.mappers;

import com.eliassantos.demoparkapi.entity.Usuario;
import com.eliassantos.demoparkapi.web.dto.UsuarioCreateDTO;
import com.eliassantos.demoparkapi.web.dto.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioCreateDTO userDto){
        Usuario user = new Usuario();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        return user;
    }

    public UsuarioResponseDTO toDTO(Usuario user) {
        var role = user.getRole().name().substring("ROLE_".length());
        return new UsuarioResponseDTO(user.getId(), user.getUsername(), role);
    }

    public List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios){
        return usuarios.stream().map(user -> toDTO(user)).collect(Collectors.toList());
//        List<UsuarioResponseDTO> listDTO = new ArrayList<>();
//        for (Usuario user: usuarios) {
//            listDTO.add(toDTO(user));
//        }
//        return listDTO;
    }
}
