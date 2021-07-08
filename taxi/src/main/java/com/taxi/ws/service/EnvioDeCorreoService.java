package com.taxi.ws.service;

import com.taxi.ws.models.SolicitudTransporte;
import com.taxi.ws.models.dto.DatosDeUsuario;
import reactor.core.publisher.Mono;

public interface EnvioDeCorreoService {

    Mono<String> envioDeCorreo(DatosDeUsuario datosDeUsuario) ;


    Mono<String> envioDeCorreo(DatosDeUsuario datosDeUsuario , SolicitudTransporte solicitudTransporte);
}
