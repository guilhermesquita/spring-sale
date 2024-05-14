package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateClientDTO;
import com.example.desafio_precojusto.DTOs.UpdateClientDTO;
import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientBusinessTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientBusiness clientBusiness;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        clientBusiness = new ClientBusiness(clientRepository);
    }

    @Test
    public void testCreateClient() {
        CreateClientDTO createClientDTO = new CreateClientDTO("Nome do Cliente", true);
        Client savedClient = new Client(UUID.randomUUID(), "Nome do Cliente", true, Instant.now(), null);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        UUID clientId = clientBusiness.createClient(createClientDTO);

        verify(clientRepository, times(1)).save(any(Client.class));

        assertEquals(savedClient.getIdClient(), clientId);
    }

    @Test
    public void testUpdateById() {
        UUID clientId = UUID.randomUUID();
        UpdateClientDTO updateClientDTO = new UpdateClientDTO("Novo Nome", false);
        Client existingClient = new Client(clientId, "Nome Antigo", true, Instant.now(), null);
        Client updatedClient = new Client(clientId, "Novo Nome", false, existingClient.getCreated_at(), Instant.now());

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        UUID updatedClientId = clientBusiness.updateById(clientId, updateClientDTO);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(any(Client.class));

        assertEquals(clientId, updatedClientId);
    }
}