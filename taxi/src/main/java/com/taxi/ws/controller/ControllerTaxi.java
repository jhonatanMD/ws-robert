package com.taxi.ws.controller;

import com.taxi.ws.models.dto.DatosDeUsuario;
import com.taxi.ws.models.dto.LoginDto;
import com.taxi.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
@RequestMapping("/taxi")
public class ControllerTaxi {


    private DatosDeUsuario usuarioSesion;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private PaginasService paginasService;

    @Autowired
    private RolService rolService;

    @GetMapping("/inicio")
    public Mono<String> inicio(HttpServletResponse resp) {

        return Mono.just("inicio");
    }

    @PostMapping("/login")
    public Mono<String> login(Model model , HttpServletRequest req, HttpServletResponse resp){

        DatosDeUsuario datosDeUsuario = new DatosDeUsuario();
        HttpSession sesion = req.getSession();
        String username,password;
        username = req.getParameter("usuario");
        password = req.getParameter("password");

        return usuarioService.logeoUsuario(new LoginDto(username, password)).flatMap(usuario -> {
            datosDeUsuario.setUsuario(usuario);
            return  empleadoService.listarEmpleadoPorId(usuario.getId_empleado()).flatMap(empleado -> {
                datosDeUsuario.setEmpleado(empleado);
                return empresaService.listarEmpresaPorId(empleado.getId_empresa()).flatMap(empresa -> {
                    datosDeUsuario.setEmpresa(empresa);
                    return rolService.listarRolesPorId(usuario.getId_rol()).flatMap(rol -> {
                        datosDeUsuario.setRol(rol);
                        return paginasService.listarPaginasPorId(rol.getPaginas()).collectList().flatMap( paginas -> {
                            datosDeUsuario.setPaginas(paginas);
                            sesion.setAttribute("usuario",datosDeUsuario);
                            return principal(model,req);
                        });
                    });
                });
            });
        }).switchIfEmpty(Mono.just("inicio"));

    }
    @GetMapping("/mapa")
    public Mono<String>mapa(Model model,HttpServletRequest req ) {

        return validation(req,"mapa").map(pagina -> {
            if(req.getSession().getAttribute("usuario") != null) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                model.addAttribute("navegaciones", usuarioSesion.getPaginas());
            }
            return  pagina;
        });
    }

    @GetMapping("/control-transporte")
    public Mono<String> control_transporte(Model model,HttpServletRequest req ) {
        return validation(req,"control-transporte").map(pagina -> {
            if(req.getSession().getAttribute("usuario") != null) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                model.addAttribute("navegaciones", usuarioSesion.getPaginas());
            }
            return  pagina;
        });
    }

    @GetMapping("/principal")
    public Mono<String> principal(Model model, HttpServletRequest req) {

        return validation(req,"principal").map(pagina -> {
            if(req.getSession().getAttribute("usuario") != null) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                model.addAttribute("navegaciones", usuarioSesion.getPaginas());
            }
            return  pagina;
        });
    }

    @GetMapping("/control-transporte-admin")
    public Mono<String> control_transporte_admin(Model model, HttpServletRequest req) {

        return validation(req,"control-transporte-admin").map(pagina -> {
            if(req.getSession().getAttribute("usuario") != null) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                model.addAttribute("navegaciones", usuarioSesion.getPaginas());
            }
            return  pagina;
        });
    }

    @GetMapping("/empleado")
    public Mono<String> usuario(Model model,HttpServletRequest req, HttpServletResponse resp) {
        return  validation(req,"empleado").flatMap(pagina -> {
            if(req.getSession().getAttribute("usuario") != null) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                model.addAttribute("navegaciones", usuarioSesion.getPaginas());
            }
            return this.edades().flatMap(edades -> {
                model.addAttribute("edades",edades);
                return  cargoService.listarCargoPorEmpresa(usuarioSesion.getEmpresa().getId()).collectList().map(cargo ->{
                    model.addAttribute("cargos",cargo);
                    return pagina;
                });
            });

        });
    }
    @GetMapping("/cerrar")
    public Mono<String> cerrar(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return  validation(req,"inicio");

    }

    private  Mono<String> validation(HttpServletRequest req , String page) {

        return Mono.just(req).map(r -> {

            if(req.getSession().getAttribute("usuario")!= null ) {
                usuarioSesion = (DatosDeUsuario) req.getSession().getAttribute("usuario");
                return page;
            }
            else
                return "inicio";

        });

    }


    private Mono<List<Integer>> edades(){
        List<Integer> edades = new ArrayList<>();
        for(int edad = 1 ; edad < 100 ; edad ++)
            edades.add(edad);

        return Mono.just(edades);
    }
}
