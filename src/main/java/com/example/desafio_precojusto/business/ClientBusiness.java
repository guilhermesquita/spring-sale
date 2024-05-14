package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.entity.Client;
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
        Client client = new Client(
          UUID.randomUUID(), createClientDTO.name_client(), createClientDTO.descont(),Instant.now(),
                null
        );
        clientRepository.save(client);
        return "Criado com sucesso!";
    }
    public Optional<Client> getClientById(UUID id){
        var clientById = clientRepository.findById(id);
        if(clientById.isPresent()){
            return clientById;
        }else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
    public void deleteClient(UUID id){
        var clientExists = clientRepository.existsById(id);
        if(clientExists){
            clientRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
}
