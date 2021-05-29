package com.taxi.ws.service.impl;

import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.LoginDto;
import com.taxi.ws.repository.UsuarioRepository;
import com.taxi.ws.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Flux<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Mono<Usuario> guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Mono<Usuario> buscarPorIdEmpleado(String idEmpleado) {
        return null;
    }

    @Override
    public Mono<Usuario> actualizarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Mono<Usuario> logeoUsuario(LoginDto login) {
        return usuarioRepository.findByUsuarioAndPassword(login.getUsuario(),login.getPassword());
    }
}
