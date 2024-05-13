package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.DTOs.CreateDuckDTO;
import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientBusiness {

    private ClientRepository clientRepository;
    public ClientBusiness(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String createClient(CreateClientDTO createClientDTO){
        var client = new Client(
          UUID.randomUUID(), createClientDTO.name_client(), createClientDTO.type_client(),Instant.now(),
                null
        );
        clientRepository.save(client);
        return "Criado com sucesso!";
    }
}
