package com.example.desafio_precojusto.repository;

import com.example.desafio_precojusto.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    @Query("SELECT s FROM Sale s WHERE s.idDuck.idDuck = :duckId")
    Sale findByDuckId(@Param("duckId") UUID duckId);
}
