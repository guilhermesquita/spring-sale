package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.business.DuckBusiness;
import com.example.desafio_precojusto.entity.Duck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/ducks")
public class DuckController {
    private DuckBusiness duckBusiness;

    public DuckController(DuckBusiness duckBusiness) {
        this.duckBusiness = duckBusiness;
    }

    @PostMapping()
    public ResponseEntity<Duck> createDuck(@RequestBody CreateDuckDTO createDuckDTO){
        duckBusiness.CreateDuck(createDuckDTO);
        return ResponseEntity.created(URI.create("v1/ducks/")).build();
    }

   @GetMapping()
    public ResponseEntity<List<Duck>> findAll(){
        return null;
    }

   @GetMapping("/{id}")
    public ResponseEntity<Duck> findById(@PathVariable("id") Long id){
        var duck = duckBusiness.getDuckById(id);
        return duck.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
