package com.example.desafio_precojusto.repository;

import com.example.desafio_precojusto.entity.Duck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuckRepository extends JpaRepository<Duck, Long> {
}
