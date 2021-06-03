package com.taxi.ws.rest;

import com.taxi.ws.models.Empleado;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/listaPorEmpresa")
    public Flux<Empleado> listarEmpleadoPorEmpresa(HttpServletRequest req){
        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        return service.listarEmpleadoPorEmpresa(datosDeUsuario.getEmpresa().getId());
    }

    @PostMapping("/guardar")
    public Mono<Empleado> guardar(@RequestBody Empleado empleado){

        return service.guardarEmpleado(empleado);
    }




}
