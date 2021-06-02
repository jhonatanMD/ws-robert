package com.taxi.ws.service.impl;

import com.taxi.ws.models.Rol;
import com.taxi.ws.repository.RolRepository;
import com.taxi.ws.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;

    @Override
    public Flux<Rol> listarRoles() {
        return repository.findAll();
    }

    @Override
    public Flux<Rol> listarRolesPorIdEmpresa(String id_empresa) {
        return repository.findByIdEmpresa(id_empresa);
    }

    @Override
    public Mono<Rol> listarRolesPorId(String id) {
        return repository.findById(id);
    }
}
