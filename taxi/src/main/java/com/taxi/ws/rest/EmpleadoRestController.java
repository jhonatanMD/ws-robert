package com.taxi.ws.rest;

import com.taxi.ws.models.Empleado;
import com.taxi.ws.models.dto.DatosDeEmpleado;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.service.CargoService;
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

    @Autowired
    private CargoService cargoService;

    @GetMapping("/listar")
    public Flux<Empleado> listarEmpleado(){
        return service.listarEmpleados();
    }

    @GetMapping("/listarPorEmpresa/{id}")
    public Flux<Empleado> listarEmpleadoPorEmpresa(@PathVariable("id") String id){
        return service.listarEmpleadoPorEmpresa(id);
    }

    @GetMapping("/listaPorEmpresa")
    public Flux<DatosDeEmpleado> listarEmpleadoPorEmpresa(HttpServletRequest req){
        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        return service.listarEmpleadoPorEmpresa(datosDeUsuario.getEmpresa().getId()).flatMap(empleado -> {
            DatosDeEmpleado datosDeEmpleado = new DatosDeEmpleado();
            datosDeEmpleado.setEmpleado(empleado);
            return cargoService.listarCargoPorId(empleado.getId_cargo()).flatMapMany(cargo -> {
                datosDeEmpleado.setCargo(cargo);
                return Flux.just(datosDeEmpleado);
            });
        });
    }

    @PostMapping("/guardar")
    public Mono<Empleado> guardar(@RequestBody Empleado empleado ,HttpServletRequest req){

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        empleado.setId_empresa(datosDeUsuario.getEmpresa().getId());
        return service.listarEmpleado(empleado.getDni(),empleado.getId_empresa())
                .flatMap(res -> {
                    empleado.setId(res.getId());
                    return service.guardarEmpleado(empleado);
        }).switchIfEmpty(service.guardarEmpleado(empleado));

    }




}
