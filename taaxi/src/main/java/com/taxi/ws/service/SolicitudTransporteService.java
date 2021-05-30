package com.taxi.ws.service;

import com.taxi.ws.models.SolicitudTransporte;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudTransporteService {

    Flux<SolicitudTransporte> listarTodosLosTransportes();
    Mono<SolicitudTransporte> guardarSolicitud(SolicitudTransporte transporte);
    Mono<SolicitudTransporte> actualizarSolicitud(SolicitudTransporte transporte);
    Flux<SolicitudTransporte> listarPorIdEmpresa(String idEmpresa);
    Flux<SolicitudTransporte> listarPorIdEmpleado(String idEmpleado);
}
