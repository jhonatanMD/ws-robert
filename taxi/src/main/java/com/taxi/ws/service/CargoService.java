package com.taxi.ws.service;

import com.taxi.ws.models.Cargo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CargoService {


    Flux<Cargo> listarCargo();
    Flux<Cargo> listarCargoPorEmpresa(String id_empresa);
    Mono<Cargo> guardarCargo(Cargo cargo);
    Mono<Cargo> actualizarCargo(String id_cargo , Cargo cargo);
}
