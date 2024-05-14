package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateSaleDTO;
import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.entity.Sale;
import com.example.desafio_precojusto.repository.ClientRepository;
import com.example.desafio_precojusto.repository.DuckRepository;
import com.example.desafio_precojusto.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SaleBusiness {
    private SaleRepository saleRepository;
    private DuckRepository duckRepository;
    private ClientRepository clientRepository;

    public SaleBusiness(SaleRepository saleRepository, DuckRepository duckRepository, ClientRepository clientRepository) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.duckRepository = duckRepository;
    }

    public UUID createSale(CreateSaleDTO createSaleDTO) {
        UUID duckId = createSaleDTO.duck_id();
        UUID clientId = createSaleDTO.client_id();
        Duck duck = null;
        Client client = null;
        Integer price = null;

        if (duckId != null) {
            Optional<Duck> duckById = duckRepository.findById(duckId);
            duck = duckById.orElse(null);
            if (duck == null) {
                throw new IllegalArgumentException("Pato não encontrado.");
            }
        }
        if (clientId != null) {
            Optional<Client> clientById = clientRepository.findById(clientId);
            client = clientById.orElse(null);
            if (client == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }
        }
        String vendido = "Vendido";
        if(duck.getStatusDuck().equalsIgnoreCase(vendido)){
            throw new IllegalArgumentException("Pato já vendido!");
        }
        if(client.getDescont() == true){
            var descont = 20 * duck.getValueDuck()/100;
            price = duck.getValueDuck() - descont;
            var sale = new Sale(
                    UUID.randomUUID(),
                    duck,
                    client,
                    price,
                    Instant.now()
                    );
            duck.setStatusDuck("Vendido");
            Sale saleSaved = saleRepository.save(sale);
            return saleSaved.getIdSale();
        }else {
            var sale = new Sale(
                    UUID.randomUUID(),
                    duck,
                    client,
                    duck.getValueDuck(),
                    Instant.now()
            );
            duck.setStatusDuck("Vendido");
            Sale saleSaved = saleRepository.save(sale);
        return saleSaved.getIdSale();
    }
}

    public List<Sale> getAllSales(){ return saleRepository.findAll(); }


}
