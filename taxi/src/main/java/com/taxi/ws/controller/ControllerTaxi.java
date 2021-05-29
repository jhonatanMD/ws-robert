package com.taxi.ws.controller;

import com.taxi.ws.models.Usuario;
import com.taxi.ws.models.dto.LoginDto;
import com.taxi.ws.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/taxi")
public class ControllerTaxi {


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/inicio")
    public String inicio() {

        return "inicio";
    }

    @PostMapping("/login")
    public Mono<String> login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession sesion = req.getSession();
        String username,password;
        username = req.getParameter("usuario");
        password = req.getParameter("password");

        return usuarioService.logeoUsuario(new LoginDto(username, password)).map(usuario -> {
            sesion.setAttribute("usuario",usuario);
            return "redirect:principal";
        }).switchIfEmpty(Mono.just("inicio"));

    }
    @GetMapping("/mapa")
    public Mono<String>mapa(HttpServletRequest req ) {

        return validation(req,"mapa");
    }

    @GetMapping("/control-transporte")
    public Mono<String> control_transporte(HttpServletRequest req ) {
        return validation(req,"control-transporte");
    }

    @GetMapping("/principal")
    public Mono<String> principal(HttpServletRequest req) {

        return validation(req,"principal");
    }

    private  Mono<String> validation(HttpServletRequest req , String page) {

        return Mono.just(req).map(r -> {

            if(req.getSession().getAttribute("usuario")!= null)
                return page;
            else
                return "inicio";

        });

    }
}
