package com.taxi.ws.repository;

import com.taxi.ws.models.SolicitudTransporte;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SolicitudTransporteRepository extends ReactiveCrudRepository<SolicitudTransporte,String> {
}
