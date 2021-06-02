package com.taxi.ws.service;

import com.taxi.ws.models.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolService {

    Flux<Rol> listarRoles ();
    Flux<Rol> listarRolesPorIdEmpresa (String id_empresa);
    Mono<Rol> listarRolesPorId (String id);
}
