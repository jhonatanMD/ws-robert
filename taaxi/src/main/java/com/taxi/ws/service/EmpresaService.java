package com.taxi.ws.service;

import com.taxi.ws.models.Empresa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmpresaService {

    Mono<Empresa> guardar(Empresa empresa);
    Flux<Empresa> listarEmpresa();
    Mono<Empresa> listarEmpresaPorId(String id_empresa);
    Mono<Empresa> actualizarEmpresa(String id_empresa , Empresa empresa);
}
