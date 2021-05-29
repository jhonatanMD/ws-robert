package com.taxi.ws.service.impl;

import com.taxi.ws.models.Empleado;
import com.taxi.ws.repository.EmpleadoRepository;
import com.taxi.ws.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

     @Autowired
     private EmpleadoRepository repository;

    @Override
    public Mono<Empleado> guardarEmpleado(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public Flux<Empleado> listarEmpleados() {
        return repository.findAll();
    }

    @Override
    public Flux<Empleado> listarEmpleadoPorEmpresa(String id_empresa) {
        return repository.findEmpleadoByEmpresa(id_empresa);
    }

    @Override
    public Mono<Empleado> listarEmpleadoPorId(String id_empleado) {
        return repository.findById(id_empleado);
    }
}
