package com.taxi.ws.service.impl;

import com.taxi.ws.models.Cargo;
import com.taxi.ws.repository.CargoRepository;
import com.taxi.ws.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository repository;


    @Override
    public Flux<Cargo> listarCargo() {
        return repository.findAll();
    }

    @Override
    public Flux<Cargo> listarCargoPorEmpresa(String id_empresa) {
        return repository.findByIdEmpresa(id_empresa);
    }

    @Override
    public Mono<Cargo> guardarCargo(Cargo cargo) {
        return repository.save(cargo);
    }

    @Override
    public Mono<Cargo> actualizarCargo(String id_cargo, Cargo cargo) {
        cargo.setId(id_cargo);
        return repository.save(cargo);
    }
}
