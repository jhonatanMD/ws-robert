package com.taxi.ws.rest;

import com.taxi.ws.models.Empleado;
import com.taxi.ws.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoRestController {


    @Autowired
    private EmpleadoService service;

    @GetMapping("/listar")
    public Flux<Empleado> listarEmpleado(){
        return service.listarEmpleados();
    }

    @GetMapping("/listarPorEmpresa/{id}")
    public Flux<Empleado> listarEmpleadoPorEmpresa(@PathVariable("id") String id){
        return service.listarEmpleadoPorEmpresa(id);
    }

    @PostMapping("/guardar")
    public Mono<Empleado> guardar(@RequestBody Empleado empleado){

        return service.guardarEmpleado(empleado);
    }




}
