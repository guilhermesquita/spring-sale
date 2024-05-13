package com.example.desafio_precojusto.repository;

import com.example.desafio_precojusto.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
