package com.taxi.ws.rest;

import com.taxi.ws.models.Empresa;
import com.taxi.ws.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaRestController {


    @Autowired
    private EmpresaService service;

    @GetMapping("/listar")
    public Flux<Empresa> listarEmpresa(){
        return service.listarEmpresa();
    }

    @GetMapping("/listarPorId/{id}")
    public Mono<Empresa> listarPorId(@PathVariable("id") String id){

        return service.listarEmpresaPorId(id);
    }

    @PostMapping("/guardar")
    public Mono<Empresa> guardar(@RequestBody Empresa empresa){

        return service.guardar(empresa);
    }

    @PostMapping("/actualizar/{id}")
    public Mono<Empresa> actualizar(@RequestBody Empresa empresa,@PathVariable("id") String id){
        return service.actualizarEmpresa(id,empresa);
    }

}

