package com.eliassantos.demoparkapi.web.controller;

import com.eliassantos.demoparkapi.entity.Usuario;
import com.eliassantos.demoparkapi.service.UsuarioService;
import com.eliassantos.demoparkapi.web.dto.UsuarioCreateDTO;
import com.eliassantos.demoparkapi.web.dto.UsuarioResponseDTO;
import com.eliassantos.demoparkapi.web.dto.UsuarioSenhaDTO;
import com.eliassantos.demoparkapi.web.dto.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create (@RequestBody UsuarioCreateDTO request){
        Usuario user = usuarioService.salvar(usuarioMapper.toUsuario(request));
        var response = usuarioMapper.toDTO(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById (@PathVariable Long id){
        Usuario user = usuarioService.buscarPorId(id);
        var response = usuarioMapper.toDTO(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword (@PathVariable Long id, @RequestBody UsuarioSenhaDTO request){
        Usuario user = usuarioService.editarSenha(id, request.senhaAtual(), request.novaSenha(), request.confirmarSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll (){
        List<Usuario> users = usuarioService.buscarTodos();
        var response = usuarioMapper.toListDTO(users);
        return ResponseEntity.ok(response);
    }
}
