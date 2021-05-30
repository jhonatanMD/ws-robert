package com.taxi.ws.service.impl;

import com.taxi.ws.models.Empresa;
import com.taxi.ws.repository.EmpresaRepository;
import com.taxi.ws.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpresaServiceImpl implements EmpresaService {


    @Autowired
    private EmpresaRepository repository;

    @Override
    public Mono<Empresa> guardar(Empresa empresa) {
        return repository.save(empresa);
    }

    @Override
    public Flux<Empresa> listarEmpresa() {
        return repository.findAll();
    }

    @Override
    public Mono<Empresa> listarEmpresaPorId(String id_empresa) {
        return repository.findById(id_empresa);
    }

    @Override
    public Mono<Empresa> actualizarEmpresa(String id_empresa, Empresa empresa) {
        empresa.setId(id_empresa);
        return repository.save(empresa);
    }
}
