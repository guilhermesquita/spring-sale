package com.example.desafio_precojusto.repository;

import com.example.desafio_precojusto.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
