package com.taxi.ws.service;

import com.taxi.ws.models.Empleado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmpleadoService {

    Mono<Empleado> guardarEmpleado(Empleado empleado);
    Flux<Empleado> listarEmpleados();
    Flux<Empleado> listarEmpleadoPorEmpresa(String id_empresa);
    Mono<Empleado> listarEmpleadoPorId(String id_empleado);
    Mono<Empleado> listarEmpleado(String dni,String id_empresa);
}
