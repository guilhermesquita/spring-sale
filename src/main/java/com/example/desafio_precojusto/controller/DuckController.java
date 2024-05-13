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
    public String createDuck(@RequestBody CreateDuckDTO createDuckDTO){
        duckBusiness.createDuck(createDuckDTO);
        ResponseEntity.created(URI.create("v1/ducks/")).build();
        return "criado!";
    }

   @GetMapping()
    public ResponseEntity<List<Duck>> findAll(){
        var ducks = duckBusiness.listUser();
        return ResponseEntity.ok(ducks);
    }

  /* @GetMapping("/{id}")
    public ResponseEntity<Duck> findById(@PathVariable("id") Long id){
        var duck = duckBusiness.getDuckById(id);
        return duck.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        duckBusiness.deleteById(id);
        return "Removido com Sucesso!";
    }
}
