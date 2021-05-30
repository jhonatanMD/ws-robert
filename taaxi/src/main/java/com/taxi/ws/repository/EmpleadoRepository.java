package com.taxi.ws.repository;

import com.taxi.ws.models.Empleado;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EmpleadoRepository extends ReactiveCrudRepository<Empleado,String> {

    @Query(value = "{'id_empresa' : ?0}")
    Flux<Empleado> findEmpleadoByEmpresa(String id_empresa);
}
