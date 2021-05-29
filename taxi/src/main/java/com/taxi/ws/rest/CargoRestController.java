package com.taxi.ws.rest;

import com.taxi.ws.models.Cargo;
import com.taxi.ws.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cargo")
public class CargoRestController {


    @Autowired
    private CargoService service;


    @GetMapping("/listar")
    public Flux<Cargo> listarCargo(){
        return service.listarCargo();
    }

    @GetMapping("/listarPorEmpresa/{id}")
    public Flux<Cargo> listarCargoPorEmpresa(@PathVariable("id") String id_empresa){
        return service.listarCargoPorEmpresa(id_empresa);
    }

    @PostMapping("/guardar")
    public Mono<Cargo> guardar(@RequestBody Cargo cargo){
        return service.guardarCargo(cargo);
    }



}
