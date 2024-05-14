package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.DTOs.CreateSaleDTO;
import com.example.desafio_precojusto.business.SaleBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/sales")
public class SaleController {
    private SaleBusiness saleBusiness;

    public SaleController(SaleBusiness saleBusiness) {
        this.saleBusiness = saleBusiness;
    }
    @PostMapping()
    public String createSale(@RequestBody CreateSaleDTO createSaleDTO){
        UUID sale = saleBusiness.createSale(createSaleDTO);
        ResponseEntity.created(URI.create("v1/sales/")).build();
        return "Compra efetuada! id: " + sale;
    }
}
