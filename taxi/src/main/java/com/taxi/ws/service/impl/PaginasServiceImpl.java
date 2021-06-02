package com.taxi.ws.service.impl;

import com.taxi.ws.models.Paginas;
import com.taxi.ws.repository.PaginasRepository;
import com.taxi.ws.service.PaginasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class PaginasServiceImpl implements PaginasService {

    @Autowired
    private PaginasRepository repository;

    @Override
    public Flux<Paginas> listarPaginas() {
        return repository.findAll();
    }

    @Override
    public Flux<Paginas> listarPaginasPorId(List<String> ids_paginas) {
        return repository.findAllById(ids_paginas);
    }
}
