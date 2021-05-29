package com.taxi.ws.rest;

import com.taxi.ws.models.Usuario;
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

    @GetMapping("/correo")
    public Mono<String> envioCorreo(HttpServletRequest req){

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        System.out.println(usuario.getUsuario());

        return Mono.empty();//service.envioDeCorreo("deinharafiorella14@gmail.com");
    }


}
