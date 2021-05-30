package com.taxi.ws.rest;

import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.LoginDto;
import com.taxi.ws.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/listarUsuario")
    public Flux<Usuario> listarUsuario(){

        return service.listarTodosUsuarios();
    }

    @PostMapping("/login")
    public Mono<Usuario> login(@RequestBody LoginDto login){
        return service.logeoUsuario(login);
    }

}
