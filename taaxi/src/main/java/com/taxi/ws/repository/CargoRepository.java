package com.taxi.ws.repository;

import com.taxi.ws.models.Cargo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CargoRepository extends ReactiveCrudRepository<Cargo,String> {

    @Query("{ 'id_empresa' : ?0}")
    Flux<Cargo> findByIdEmpresa(String id_empresa);
}
