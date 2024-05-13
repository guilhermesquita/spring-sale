package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.business.ClientBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
