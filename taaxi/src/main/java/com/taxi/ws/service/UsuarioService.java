package com.taxi.ws.service;

import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.LoginDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

    Flux<Usuario> listarTodosUsuarios();
    Mono<Usuario> guardarUsuario(Usuario usuario);
    Mono<Usuario> buscarPorIdEmpleado(String idEmpleado);
    Mono<Usuario> actualizarUsuario(Usuario usuario);
    Mono<Usuario> logeoUsuario(LoginDto login);

}
