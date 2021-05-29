package com.taxi.ws.service.impl;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.repository.SolicitudTransporteRepository;
import com.taxi.ws.service.SolicitudTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class SolicitudTransporteImpl implements SolicitudTransporteService {

    @Autowired
    private SolicitudTransporteRepository repository;

    @Override
    public Flux<SolicitudTransporte> listarTodosLosTransportes() {
        return repository.findAll();
    }

    @Override
    public Mono<SolicitudTransporte> guardarSolicitud(SolicitudTransporte transporte) {
        transporte.setFecha_solicitud(LocalDate.now());
        return repository.save(transporte);
    }

    @Override
    public Mono<SolicitudTransporte> actualizarSolicitud(SolicitudTransporte transporte) {
        return null;
    }

    @Override
    public Flux<SolicitudTransporte> listarPorIdEmpresa(String idEmpresa) {
        return null;
    }

    @Override
    public Flux<SolicitudTransporte> listarPorIdEmpleado(String idEmpleado) {
        return repository.findByEmpleado(idEmpleado);
    }
}
