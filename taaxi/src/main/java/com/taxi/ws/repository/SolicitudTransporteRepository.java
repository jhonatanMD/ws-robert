package com.taxi.ws.repository;

import com.taxi.ws.models.SolicitudTransporte;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SolicitudTransporteRepository extends ReactiveCrudRepository<SolicitudTransporte,String> {


    @Query("{ 'id_empleado' : ?0}")
    Flux<SolicitudTransporte> findByEmpleado(String id_empleadp);
}
