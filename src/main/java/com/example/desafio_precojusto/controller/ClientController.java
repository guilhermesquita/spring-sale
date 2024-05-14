package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.business.ClientBusiness;
import com.example.desafio_precojusto.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("v1/clients")
public class ClientController {
    private ClientBusiness clientBusiness;

    public ClientController(ClientBusiness clientBusiness) {
        this.clientBusiness = clientBusiness;
    }

    @PostMapping()
    public String createClient(@RequestBody CreateClientDTO createClientDTO){
        clientBusiness.createClient(createClientDTO);
        ResponseEntity.created(URI.create("v1/clients/")).build();
        return "Cliente Criado!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable UUID id){
        var client = clientBusiness.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
