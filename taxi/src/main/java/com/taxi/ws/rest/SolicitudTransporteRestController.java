package com.taxi.ws.rest;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.models.dto.SolicitudTransporteDto;
import com.taxi.ws.service.EmpleadoService;
import com.taxi.ws.service.SolicitudTransporteService;
import com.taxi.ws.service.UsuarioService;
import com.taxi.ws.service.impl.EnvioDeCorreoServiceImpl;
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

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EnvioDeCorreoServiceImpl envioDeCorreoService;

    
    @GetMapping("/listarTransporte")
    public Flux<SolicitudTransporteDto> listarTransporte(HttpServletRequest req ){

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        return service.listarTodosLosTransportes().filter(res -> res.getId_empresa().equals(datosDeUsuario.getEmpresa().getId())).flatMap(r -> {
            r.setCod_estado(Constantes.ESTADO_TRANSPORTE.get(r.getCod_estado()));
            return empleadoService.listarEmpleadoPorId(r.getId_empleado()).map(emp -> {
                emp.setNombre(emp.getNombre()+" "+emp.getApe_pat()+" "+emp.getApe_mat());
                return SolicitudTransporteDto.builder().taxi(r).empleado(emp).build();
            });
        });
    }

    @GetMapping("/listarTransportePag/{pag}")
    public Flux<SolicitudTransporte> listarTransporte(HttpServletRequest req ,@PathVariable("pag") int pag){

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        return service.listarPorIdEmpleadoPaginacion(pag,datosDeUsuario.getUsuario().getId_empleado());
    }

    @PostMapping("/guardarTransporte")
    public Mono<SolicitudTransporte> guardarTransporte(@RequestBody SolicitudTransporte transporte,HttpServletRequest req){

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
        transporte.setId_empleado(datosDeUsuario.getEmpleado().getId());
        transporte.setId_empresa(datosDeUsuario.getEmpresa().getId());
        double precio = Long.parseLong(transporte.getKilometraje()) * Constantes.PRECIO_POR_METRO;
        transporte.setPrecio(new BigDecimal(Math.ceil(precio)));
        return service.guardarSolicitud(transporte).flatMap(d ->
            envioDeCorreoService.envioDeCorreo(datosDeUsuario,d).map(r -> d));
    }

    @GetMapping("/listarTransportePorEmpleado")
    public Flux<SolicitudTransporte> listarTransportePorEmpleado(HttpServletRequest req){
        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");
         return service.listarPorIdEmpleado(datosDeUsuario.getUsuario().getId_empleado()).map(r -> {

            r.setCod_estado(Constantes.ESTADO_TRANSPORTE.get(r.getCod_estado()));

            return r;
        });
    }

}
