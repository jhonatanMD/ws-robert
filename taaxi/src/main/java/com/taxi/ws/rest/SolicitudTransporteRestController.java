package com.taxi.ws.rest;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.service.SolicitudTransporteService;
import com.taxi.ws.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

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
    public Mono<SolicitudTransporte> guardarTransporte(@RequestBody SolicitudTransporte transporte,HttpServletRequest req){

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        transporte.setId_empleado(datosDeUsuario.getEmpleado().getId());
        transporte.setId_empresa(datosDeUsuario.getEmpresa().getId());
        double precio = Long.parseLong(transporte.getKilometraje()) * Constantes.PRECIO_POR_METRO;
        transporte.setPrecio(new BigDecimal(Math.ceil(precio)));
        return service.guardarSolicitud(transporte);
    }

    @GetMapping("/listarTransportePorEmpleado")
    public Flux<SolicitudTransporte> listarTransportePorEmpleado(HttpServletRequest req){
        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");

        System.out.println(datosDeUsuario.getUsuario().getId_empleado());
         return service.listarPorIdEmpleado(datosDeUsuario.getUsuario().getId_empleado());
    }

}
