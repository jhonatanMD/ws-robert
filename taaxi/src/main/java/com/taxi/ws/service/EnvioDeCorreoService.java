package com.taxi.ws.service;

import reactor.core.publisher.Mono;

public interface EnvioDeCorreoService {

    Mono<String> envioDeCorreo(String email);
}
