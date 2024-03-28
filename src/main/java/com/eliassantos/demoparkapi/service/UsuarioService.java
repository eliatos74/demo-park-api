package com.eliassantos.demoparkapi.service;

import com.eliassantos.demoparkapi.entity.Usuario;
import com.eliassantos.demoparkapi.exception.EntityNotFoundException;
import com.eliassantos.demoparkapi.exception.PasswordInvalidException;
import com.eliassantos.demoparkapi.exception.UsernameUniqueViolationException;
import com.eliassantos.demoparkapi.repository.UsuarioRepository;
import com.eliassantos.demoparkapi.web.dto.UsuarioCreateDTO;
import com.eliassantos.demoparkapi.web.dto.UsuarioSenhaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario user) {
        try {
            return usuarioRepository.save(user);
        }catch (org.springframework.dao.DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", user.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado.",id))
        );
    }
    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)){
            throw new PasswordInvalidException("A senha não confere com a confirmação de senha...");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)){
            throw new PasswordInvalidException("Sua senha não confere...");
        }

        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

}
