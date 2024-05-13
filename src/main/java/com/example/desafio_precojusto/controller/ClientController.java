package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("v1/clients")
public class ClientController {
    @PostMapping()
    public String createClient(@RequestBody CreateDuckDTO createDuckDTO){
        return null;
    }
}
