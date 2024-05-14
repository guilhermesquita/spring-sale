package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.DTOs.UpdateClientDTO;
import com.example.desafio_precojusto.business.ClientBusiness;
import com.example.desafio_precojusto.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
        var client = clientBusiness.createClient(createClientDTO);
        ResponseEntity.created(URI.create("v1/clients/")).build();
        return "Cliente Criado! id: " + client.toString();
    }

    @GetMapping()
    public  ResponseEntity<List<Client>> findAll(){
        var client = clientBusiness.listClient();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") UUID id){
        var client = clientBusiness.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("{id}")
    public String editClient(@PathVariable("id") UUID id, @RequestBody UpdateClientDTO updateClientDTO){
        var business = clientBusiness.updateById(id, updateClientDTO);
        return "Atualizado com sucesso! id: " + business.toString();
    }

    @DeleteMapping("{id}")
    public String deleteClient(@PathVariable("id") UUID id){
        clientBusiness.deleteClient(id);
        return "Removido com sucesso";
    }
}
