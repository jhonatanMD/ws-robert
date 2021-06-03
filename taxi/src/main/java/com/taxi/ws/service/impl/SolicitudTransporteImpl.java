package com.taxi.ws.service.impl;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.repository.SolicitudTransporteRepository;
import com.taxi.ws.service.SolicitudTransporteService;
import com.taxi.ws.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class SolicitudTransporteImpl implements SolicitudTransporteService {

    @Autowired
    private ReactiveMongoTemplate template;

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
        return repository.findByEmpleado(idEmpleado).map(res -> {
            res.setCod_estado(Constantes.ESTADO_TRANSPORTE.get(res.getCod_estado()));
            return res;
        });
    }

    @Override
    public Flux<SolicitudTransporte> listarPorIdEmpleadoPaginacion(int pg1 ,String idEmpleado) {

        Query query = new Query();
        query.skip(pg1 * 10);
        query.limit(10);
        query.addCriteria(Criteria.where("id_empleado").is(idEmpleado));

        return template.find(query,SolicitudTransporte.class).map(res -> {
            res.setCod_estado(Constantes.ESTADO_TRANSPORTE.get(res.getCod_estado()));
            return res;
        });
    }
}
