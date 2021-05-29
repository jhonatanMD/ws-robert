package com.taxi.ws.rest;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.service.SolicitudTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transporte")
public class SolicitudTransporteRestController {


    @Autowired
    private SolicitudTransporteService service;

    @GetMapping("/listarTransporte")
    public Flux<SolicitudTransporte> listarTransporte(){

        return service.listarTodosLosTransportes();
    }

    @PostMapping("/guardarTransporte")
    public Mono<SolicitudTransporte> guardarTransporte(@RequestBody SolicitudTransporte transporte){

        return service.guardarSolicitud(transporte);
    }

}
