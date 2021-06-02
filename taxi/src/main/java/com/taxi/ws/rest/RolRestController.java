package com.taxi.ws.rest;

import com.taxi.ws.models.Rol;
import com.taxi.ws.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/roles")
public class RolRestController {


    @Autowired
    private RolService service;

    @GetMapping("/listar")
    public Flux<Rol> listar(){
        return service.listarRoles();
    }

    @GetMapping("/listarPorEmpresa/{id_empresa}")
    public Flux<Rol> listarPorEmpresa(@PathVariable("id_empresa") String id_empresa){
        return service.listarRolesPorIdEmpresa(id_empresa);
    }

    @GetMapping("/listarPorId/{id}")
    public Mono<Rol> listarPorId(@PathVariable("id") String id){
        return service.listarRolesPorId(id);
    }

}
