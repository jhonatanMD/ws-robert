package com.taxi.ws.repository;

import com.taxi.ws.models.Empresa;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmpresaRepository extends ReactiveCrudRepository<Empresa,String> {
}
