package com.taxi.ws.repository;

import com.taxi.ws.models.Paginas;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PaginasRepository extends ReactiveCrudRepository<Paginas,String> {
}
