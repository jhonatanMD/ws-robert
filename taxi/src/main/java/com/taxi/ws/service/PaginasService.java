package com.taxi.ws.service;

import com.taxi.ws.models.Paginas;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PaginasService {


    Flux<Paginas> listarPaginas();

    Flux<Paginas> listarPaginasPorId(List<String> ids_paginas);
}
