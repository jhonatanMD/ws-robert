package com.taxi.ws.repository;

import com.taxi.ws.models.Rol;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RolRepository extends ReactiveCrudRepository<Rol,String> {

    @Query("{'id_Empresa':?0}")
    Flux<Rol> findByIdEmpresa(String id_empresa);
}
