package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.DTOs.CreateSaleDTO;
import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.entity.Sale;
import com.example.desafio_precojusto.repository.ClientRepository;
import com.example.desafio_precojusto.repository.DuckRepository;
import com.example.desafio_precojusto.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleBusinessTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private DuckRepository duckRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private SaleBusiness saleBusiness;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateSale() {
        UUID duckId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Duck duck = new Duck(duckId, "Pato Donald", "Disponível", 100, null, Instant.now(), Instant.now());
        Client client = new Client(clientId, "John Doe", false, Instant.now(), Instant.now());

        when(duckRepository.findById(duckId)).thenReturn(Optional.of(duck));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(saleRepository.save(any(Sale.class))).thenAnswer(invocation -> {
            Sale sale = invocation.getArgument(0);
            return sale;
        });

        CreateSaleDTO createSaleDTO = new CreateSaleDTO(duckId, clientId);

        UUID saleId = saleBusiness.createSale(createSaleDTO);

        verify(duckRepository).findById(duckId);
        verify(clientRepository).findById(clientId);
        verify(saleRepository).save(any(Sale.class));

        assertNotNull(saleId);
    }
}