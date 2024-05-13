package com.example.desafio_precojusto.repository;

import com.example.desafio_precojusto.entity.Duck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DuckRepository extends JpaRepository<Duck, UUID> {
}
