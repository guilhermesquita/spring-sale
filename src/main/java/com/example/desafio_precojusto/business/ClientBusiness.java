package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.DTOs.UpdateClientDTO;
import com.example.desafio_precojusto.DTOs.UpdateDuckDTO;
import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientBusiness {

    private ClientRepository clientRepository;
    public ClientBusiness(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public UUID createClient(CreateClientDTO createClientDTO){
        Client client = new Client(
          UUID.randomUUID(), createClientDTO.name_client(), createClientDTO.descont(),Instant.now(),
                null
        );
        Client clientSaved = clientRepository.save(client);
        return clientSaved.getIdClient();
    }
    public Optional<Client> getClientById(UUID id){
        var clientById = clientRepository.findById(id);
        if(clientById.isPresent()){
            return clientById;
        }else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public List<Client> listClient(){
        return clientRepository.findAll();
    }
    public void deleteClient(UUID id){
        var clientExists = clientRepository.existsById(id);
        if(clientExists){
            clientRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public UUID updateById(UUID id, UpdateClientDTO updateClientDTO){
        var clientExists = clientRepository.findById(id);
        if(clientExists.isPresent()){
            var client = clientExists.get();

            if(updateClientDTO.name_client() != null){
                client.setNameClient(updateClientDTO.name_client());
            }
            if(updateClientDTO.descont() != null){
                client.setDescont(updateClientDTO.descont());
            }
            Client savedClient = clientRepository.save(client);
            return savedClient.getIdClient();
        }else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
}
