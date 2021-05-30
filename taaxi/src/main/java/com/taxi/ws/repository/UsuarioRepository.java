package com.taxi.ws.repository;


import com.taxi.ws.models.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario,String> {


    Mono<Usuario> findByUsuarioAndPassword(String usuario , String password);
 }
