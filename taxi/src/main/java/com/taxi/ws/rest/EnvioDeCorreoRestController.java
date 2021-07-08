package com.taxi.ws.rest;

import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.service.EmpleadoService;
import com.taxi.ws.service.EmpresaService;
import com.taxi.ws.service.EnvioDeCorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/envio-correo")
public class EnvioDeCorreoRestController {


    @Autowired
    private EnvioDeCorreoService service;


    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/correo")
    public Mono<String> envioCorreo(HttpServletRequest req) throws Exception {

        DatosDeUsuario datosDeUsuario = (DatosDeUsuario) req.getSession().getAttribute("usuario");



        return service.envioDeCorreo(datosDeUsuario);/*empleadoService.listarEmpleadoPorId(usuario.getId_empleado())
                .flatMap(empleado -> service.envioDeCorreo(empleado.getEmail()));*/
    }


}
